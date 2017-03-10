package com.taotao.portal.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/**
 * @author 赵尔强
 * @Description
 * @date 2017/3/10 22:29.
 */
public interface CartService {
	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
