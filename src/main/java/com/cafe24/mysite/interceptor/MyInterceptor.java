package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handlerInfo) throws Exception {	//Object는 Handler 정보
		/**
		 * Hadler가 Object형인 이유 : 여러 개의 Handler 타입이 있기 때문에
		 * DefaultServletHadler, Controller에 만든 Handler
		 */
		System.out.println("MyInterceptor : preHandle");
		return true;
	}
	
	@Override
	public void postHandle(	// handler 함수가 끝나게 되면 호출되는 메소드
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handlerInfo, 
			ModelAndView mav)
			throws Exception {
		System.out.println("MyInterceptor : postHandle");
	}

	@Override
	public void afterCompletion(		// 뷰 작업까지 끝나면 호출되는 메소드
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handlerInfo, 
			Exception ex)
			throws Exception {
		System.out.println("MyInterceptor : afterCompletion");
	}
}
