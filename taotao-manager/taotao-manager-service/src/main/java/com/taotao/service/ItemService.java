package com.taotao.service;

import com.taotao.common.pojo.easyui.DataGridResult;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);

	DataGridResult getItemList(int page, int rows);

	TaotaoResult save(TbItem item, String desc, String itemParams);
}
