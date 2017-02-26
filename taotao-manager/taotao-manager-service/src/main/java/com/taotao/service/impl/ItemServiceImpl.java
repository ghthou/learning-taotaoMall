package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.easyui.DataGridResult;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		TbItemExample tbItemExample = new TbItemExample();
		TbItemExample.Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
		return tbItems.isEmpty() ? null : tbItems.get(0);
	}

	@Override
	public DataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(null);
		return new DataGridResult((int) ((Page) list).getTotal(), list);
	}

	@Override
	public TaotaoResult save(TbItem item) {
		item.setId(IDUtils.genItemId());
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		tbItemMapper.insert(item);
		return TaotaoResult.ok();
	}
}
