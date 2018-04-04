package com.cafe24.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	//private static Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void handlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception{
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		e.printStackTrace();
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			JSONResult jsonResult = JSONResult.fail(e.getMessage());
			String json = new ObjectMapper().writeValueAsString(jsonResult);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(json);
			return;
		}
	}
}
