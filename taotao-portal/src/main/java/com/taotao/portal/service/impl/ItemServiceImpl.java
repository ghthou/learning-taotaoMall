package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.TbItemInfo;
import com.taotao.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_INFO}")
	private String REST_ITEM_INFO;
	@Value("${REST_ITEM_PARAM}")
	private String REST_ITEM_PARAM;
	@Value("${REST_ITEM_DESC}")
	private String REST_ITEM_DESC;

	@Override
	public TbItemInfo getItemById(Long itemId) {

		try {
			//调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_INFO + itemId);
			System.out.println(json);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemInfo.class);
				if (taotaoResult.getStatus() == 200) {
					TbItemInfo item = (TbItemInfo) taotaoResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 	取商品描述
	 * <p>Title: getItemDescById</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 * @see ItemService#getItemDescById(Long)
	 */
	@Override
	public String getItemDescById(Long itemId) {
		try {
			//查询商品描述
			String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC + itemId);
			//转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
				//取商品描述信息
				String result = itemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据商品id查询规格参数
	 * <p>Title: getItemParam</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 * @see com.taotao.portal.service.ItemService#getItemParam(java.lang.Long)
	 */
	@Override
	public String getItemParam(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM + itemId);
			//把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
				String paramData = itemParamItem.getParamData();
				//生成html
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for (Map m1 : jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for (Map m2 : list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
						sb.append("            <td>" + m2.get("v") + "</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				//返回html片段
				return sb.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
