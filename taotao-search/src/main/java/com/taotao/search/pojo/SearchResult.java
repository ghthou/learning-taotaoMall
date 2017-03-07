package com.taotao.search.pojo;

import java.util.List;

import lombok.Data;

@Data
public class SearchResult {
	//商品列表
	private List<Item> itemList;
	//总记录数
	private long recordCount;
	//总页数
	private long pageCount;
	//当前页
	private long curPage;
}
