package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * @author 赵尔强
 * @Description
 * @date 2017/3/10 21:59.
 */
public interface UserService {
	TbUser getUserByToken(String token);
}
