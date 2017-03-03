package com.taotao.common.pojo.easyui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
	private long id;
	private String text;
	private String state;
}
