package com.taotao.portal.pojo;

import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import lombok.Data;

@Data
public class Order extends TbOrder {

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;

}
