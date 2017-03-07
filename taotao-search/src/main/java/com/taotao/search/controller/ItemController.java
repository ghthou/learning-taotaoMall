package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.util.TaotaoResult;
import com.taotao.search.service.ItemService;

@RestController
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	public TaotaoResult importAllItems() {
		return itemService.importAllItems();
	}
}
