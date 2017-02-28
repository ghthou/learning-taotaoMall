package com.taotao.service;

import com.taotao.common.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);

	TaotaoResult insertItemParam(TbItemParam itemParam);
}
