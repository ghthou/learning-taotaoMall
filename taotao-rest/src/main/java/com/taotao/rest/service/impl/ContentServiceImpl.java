package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> getContentList(long contentCid) {
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);

		return list;
	}

}
