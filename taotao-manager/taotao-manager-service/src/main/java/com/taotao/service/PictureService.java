package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

	String upload(MultipartFile uploadFile);
}
