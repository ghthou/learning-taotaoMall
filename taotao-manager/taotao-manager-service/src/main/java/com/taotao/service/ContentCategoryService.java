package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.easyui.TreeNode;
import com.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {

	TaotaoResult insertContentCategory(long parentId, String name);

	List<TreeNode> getCategoryList(long parentId);
}
