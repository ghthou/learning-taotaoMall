package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.ExceptionUtil;
import com.taotao.common.util.TaotaoResult;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}