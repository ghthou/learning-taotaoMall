package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/index")
	public String index(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
}
