package com.taotao.rest.service;

import org.springframework.stereotype.Service;

import com.taotao.common.util.TaotaoResult;

@Service
public interface ItemService {

	TaotaoResult getItemBaseInfo(long itemId);

	TaotaoResult getItemDesc(long itemId);

	TaotaoResult getItemParam(long itemId);
}
