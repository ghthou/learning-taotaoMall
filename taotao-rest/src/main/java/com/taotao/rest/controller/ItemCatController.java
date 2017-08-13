package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@RestController
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping(value = { "/itemcat/list", "/itemcat/all" }, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		if (StringUtils.isNotBlank(callback)) {
			return callback + "(" + json + ");";
		}
		return json;
	}
}
