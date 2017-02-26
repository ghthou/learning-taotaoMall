package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.easyui.DataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
	@Autowired
	private ItemService itemService;

	@ResponseBody
	@RequestMapping(value = "/{itemId}")
	public TbItem getItemById(@PathVariable long itemId) {
		return itemService.getItemById(itemId);
	}

	@ResponseBody
	@RequestMapping(value = "/list")
	public DataGridResult getItemList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "30") int rows) {
		return itemService.getItemList(page, rows);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public TaotaoResult save(TbItem item) {
		return itemService.save(item);
	}

}
