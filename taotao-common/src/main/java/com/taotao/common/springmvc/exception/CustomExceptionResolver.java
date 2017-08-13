package com.taotao.common.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.taotao.common.exception.CustomException;
import com.taotao.common.util.TaotaoResult;

/**
 * @Description 自定义异常处理
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	private final MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (handler instanceof HandlerMethod) {
			if (ex instanceof CustomException) {
				TaotaoResult result = ((CustomException) ex).getResult();
				return new ModelAndView(jsonView, "result", result);
			}
		}
		return new ModelAndView("index");
	}
}
