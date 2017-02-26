package com.taotao.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 自定义异常处理
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod methodHandle = (HandlerMethod) handler;
			ex.printStackTrace();
		}
		return new ModelAndView("index");
	}
}
