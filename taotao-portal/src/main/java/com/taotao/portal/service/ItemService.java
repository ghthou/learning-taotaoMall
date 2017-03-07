package com.taotao.portal.service;

import com.taotao.portal.pojo.TbItemInfo;

public interface ItemService {
	TbItemInfo getItemById(Long itemId);

	String getItemDescById(Long itemId);

	String getItemParam(Long itemId);
}
