package com.taotao.portal.pojo;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Item {
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;

	public String[] getImages() {
		if (StringUtils.isNotBlank(image)) {
			return image.split(",");
		}
		return null;
	}
}
