package com.taotao.common.pojo;

import java.util.List;

import lombok.Data;

@Data
public class EasyUIDataGridResult {
	private int total;
	private List<?> rows;

	public EasyUIDataGridResult(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}
