package com.taotao.rest.service;

import com.taotao.common.util.TaotaoResult;

public interface RedisService {
	TaotaoResult syncContent(long contentCid);

	TaotaoResult syncItemCat(long itemCatId);
}
