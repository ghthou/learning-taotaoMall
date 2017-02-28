package com.taotao.springmvc.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.exception.CustomException;
import com.taotao.common.util.JsonUtils;
import com.taotao.common.util.TaotaoResult;

/**
 * @Description 自定义异常处理
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (handler instanceof HandlerMethod) {
			if (ex instanceof CustomException) {
				try {
					CustomException e = (CustomException) ex;
					TaotaoResult result = e.getResult();
					//todo ajax类异常处理待完善
					response.getWriter().write(JsonUtils.objectToJson(result));
					return null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			//HandlerMethod methodHandle = (HandlerMethod) handler;
			//ex.printStackTrace();
		}
		return new ModelAndView("index");
	}
}
