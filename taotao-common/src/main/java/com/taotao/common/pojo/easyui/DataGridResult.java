package com.taotao.common.pojo.easyui;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataGridResult {
	private int total;
	private List<?> rows;
}
