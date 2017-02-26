package com.taotao.common.pojo.easyui;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeNode {
	private long id;
	private String text;
	private String state;
}
