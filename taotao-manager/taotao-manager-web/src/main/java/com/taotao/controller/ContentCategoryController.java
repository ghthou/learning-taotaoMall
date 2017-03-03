package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.easyui.TreeNode;
import com.taotao.common.util.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		return contentCategoryService.insertContentCategory(parentId, name);
	}
}
