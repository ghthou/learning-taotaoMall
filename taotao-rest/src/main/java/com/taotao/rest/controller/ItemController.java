package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/info/{itemId}")
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}

	@RequestMapping("/desc/{itemId}")
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}

	@RequestMapping("/param/{itemId}")
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}
}