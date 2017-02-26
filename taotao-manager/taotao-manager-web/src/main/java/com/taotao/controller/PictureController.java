package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController extends BaseController {

	@Autowired
	private PictureService pictureService;

	@ResponseBody
	@RequestMapping(value = "/upload")
	public String upload(MultipartFile uploadFile) {
		return pictureService.upload(uploadFile);
	}
}
