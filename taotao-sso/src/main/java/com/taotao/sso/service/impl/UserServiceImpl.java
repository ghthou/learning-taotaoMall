package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.redis.JedisClient;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;
	@Value("${redis.key.string.user.login}")
	private String REDIS_KEY_STRING_USER_LOGIN;
	@Value("${redis.key.string.user.login.expire}")
	private Integer REDIS_KEY_STRING_USER_LOGIN_EXPIRE;

	private final String salt = "fjsdkajfdsfds34604gd231g0ds1g2dsf321afd4s41";

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		//创建查询条件
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		//对数据进行校验：1、2、3分别代表username、phone、email
		//用户名校验
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
			//电话校验
		} else if (2 == type) {
			criteria.andPhoneEqualTo(content);
			//email校验
		} else {
			criteria.andEmailEqualTo(content);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex((salt + user.getPassword()).getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	/**
	 * 用户登录
	 * <p>Title: userLogin</p>
	 * <p>Description: </p>
	 * @param username
	 * @param password
	 * @param request
	 *@param response @return
	 */
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {

		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//如果没有此用户名
		if (list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//比对密码
		if (!DigestUtils.md5DigestAsHex((salt + password).getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		//生成token
		String token = UUID.randomUUID().toString().replace("-", "");
		//保存用户之前，把用户对象中的密码清空。
		user.setPassword(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_KEY_STRING_USER_LOGIN + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_KEY_STRING_USER_LOGIN + ":" + token, REDIS_KEY_STRING_USER_LOGIN_EXPIRE);
		//返回token
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {

		//根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_KEY_STRING_USER_LOGIN + ":" + token);
		//判断是否为空
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "此session已经过期，请重新登录");
		}
		//更新过期时间
		jedisClient.expire(REDIS_KEY_STRING_USER_LOGIN + ":" + token, REDIS_KEY_STRING_USER_LOGIN_EXPIRE);
		//返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
}
