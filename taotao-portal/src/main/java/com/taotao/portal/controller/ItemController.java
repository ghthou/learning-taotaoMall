package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.TbItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		TbItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}

	@ResponseBody
	@RequestMapping(value = "/item/desc/{itemId}")
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		return string;
	}

	@ResponseBody
	@RequestMapping(value = "/item/param/{itemId}")
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParam(itemId);
		return string;
	}
}