package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taotao.common.util.TaotaoResult;
import com.taotao.rest.service.RedisService;

@RestController
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("/content/{contentCid}")
	public TaotaoResult contentCacheSync(@PathVariable Long contentCid) {
		return redisService.syncContent(contentCid);
	}

	@RequestMapping("/itemCat/{contentCid}")
	public TaotaoResult itemCatCacheSync(@PathVariable Long contentCid) {
		return redisService.syncItemCat(contentCid);
	}
}
