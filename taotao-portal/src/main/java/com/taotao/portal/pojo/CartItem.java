package com.taotao.portal.pojo;

import lombok.Data;

@Data
public class CartItem {
	private long id;
	private String title;
	private Integer num;
	private long price;
	private String image;
}
