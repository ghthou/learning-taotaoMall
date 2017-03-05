package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.easyui.TreeNode;

public interface ItemCatService {
	List<TreeNode> getItemCatList(long parentId);
}
