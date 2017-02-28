package com.taotao.common.exception;

import com.taotao.common.util.TaotaoResult;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private TaotaoResult result;

	public CustomException(TaotaoResult result) {
		super(result.getMsg());
		this.result = result;
	}
}
