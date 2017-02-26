package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.easyui.TreeNode;
import com.taotao.service.ItemCaoService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController extends BaseController {
	@Autowired
	private ItemCaoService itemCaoService;

	@ResponseBody
	@RequestMapping(value = "/list")
	public List<TreeNode> getItemList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return itemCaoService.getItemCatList(parentId);
	}
}
