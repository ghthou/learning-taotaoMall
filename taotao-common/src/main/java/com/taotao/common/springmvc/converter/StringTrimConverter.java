package com.taotao.common.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * @Description 字符串去除前后空格转换器(包括字符串参数,及对象中的字符串类型属性)
 */
public class StringTrimConverter implements Converter<String, String> {
	@Override
	public String convert(String source) {

		if (source != null) {
			source = source.trim();
			if (!"".equals(source)) {
				return source;
			}
		}
		return null;
	}
}
