package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${redis.key.hset.content}")
	private String REDIS_KEY_HSET_CONTENT;
	@Value("${redis.key.hset.itemCat}")
	private String REDIS_KEY_HSET_ITEMCAT;

	@Override
	public TaotaoResult syncContent(long contentCid) {
		return commonHdelHandler(REDIS_KEY_HSET_CONTENT, contentCid + "");
	}

	@Override
	public TaotaoResult syncItemCat(long itemCatId) {
		return commonHdelHandler(REDIS_KEY_HSET_ITEMCAT, itemCatId + "");
	}

	private TaotaoResult commonHdelHandler(String hkey, String key) {
		try {
			jedisClient.hdel(hkey, key);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
