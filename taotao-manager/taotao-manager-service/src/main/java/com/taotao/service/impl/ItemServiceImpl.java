package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taotao.common.exception.CustomException;
import com.taotao.common.pojo.easyui.DataGridResult;
import com.taotao.common.util.IDUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

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
	public TaotaoResult save(TbItem item, String desc, String itemParams) {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//保存商品
		if (tbItemMapper.insert(item) < 1) {
			throw new CustomException(TaotaoResult.build(500, "系统异常"));
		}
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		//保存商品详情
		if (tbItemDescMapper.insert(tbItemDesc) < 1) {
			throw new CustomException(TaotaoResult.build(500, "系统异常"));
		}
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//保存商品规格
		if (tbItemParamItemMapper.insert(itemParamItem) < 1) {
			throw new CustomException(TaotaoResult.build(500, "系统异常"));
		}
		return TaotaoResult.ok();
	}
}
