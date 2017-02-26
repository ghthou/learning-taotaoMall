package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@ResponseBody
	@RequestMapping(value = "/item/{itemId}")
	public TbItem getItemById(@PathVariable long itemId) {
		return itemService.getItemById(itemId);
	}

	@ResponseBody
	@RequestMapping(value = "/item/list")
	public EasyUIDataGridResult getItemList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "30") int rows) {
		return itemService.getItemList(page, rows);
	}
}
