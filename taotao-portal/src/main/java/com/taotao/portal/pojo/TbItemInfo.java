package com.taotao.portal.pojo;

import org.apache.commons.lang3.StringUtils;

import com.taotao.pojo.TbItem;

public class TbItemInfo extends TbItem {

	public String[] getImages() {
		String image = getImage();
		if (StringUtils.isNotBlank(image)) {
			return image.split(",");
		}
		return null;
	}
}
