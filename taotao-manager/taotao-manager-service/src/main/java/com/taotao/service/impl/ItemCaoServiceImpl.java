package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.easyui.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCaoService;

@Repository
public class ItemCaoServiceImpl implements ItemCaoService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		tbItemCatExample.setOrderByClause(" sort_order");
		TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);

		List<TreeNode> list = new ArrayList<>();

		for (TbItemCat tbItemCat : tbItemCats) {
			list.add(new TreeNode(tbItemCat.getId(), tbItemCat.getName(), tbItemCat.getIsParent() ? "closed" : "open"));
		}
		return list;
	}
}
