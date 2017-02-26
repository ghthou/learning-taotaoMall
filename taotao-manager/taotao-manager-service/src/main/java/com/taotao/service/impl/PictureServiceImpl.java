package com.taotao.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.IDUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	@Value("${fileBasePath}")
	private String fileBasePath;

	@Value("${urlBasePath}")
	private String urlBasePath;

	@Override
	public String upload(MultipartFile uploadFile) {
		Map<String, Object> map = new HashMap<>();
		try {
			String dicName = new DateTime().toString("yyyy/MM/dd/");
			String fileName = IDUtils.genImageName();
			File file = new File(fileBasePath + dicName + fileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			uploadFile.transferTo(file);
			map.put("error", 0);
			map.put("url", urlBasePath + dicName + fileName);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("error", 1);
		map.put("message", "服务异常");
		return JsonUtils.objectToJson(map);
	}
}
