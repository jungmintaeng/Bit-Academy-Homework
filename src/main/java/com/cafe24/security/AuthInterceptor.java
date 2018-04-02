package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 핸들러 종류 확인
		if((handler instanceof HandlerMethod) == false) {
			return true;
		}
		
		//casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		
		// @Auth 받아오기.
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// auth가 null 이면 annotation @Auth가 달려있지 않은 메소드 --> 인증을 필요로 하지 않는 핸들러
		if(auth == null) {
			return true;
		}
		
		/**
		 * auth.role()을 사용하고 싶은 경우 auth.role() 값을 이용하면 된다.
		 */
		
		// @Auth가 붙어있는 경우
		HttpSession session = request.getSession();
		
		if( session == null || session.getAttribute("authUser") == null ) {
			response.sendRedirect(request.getContextPath()+ "/main");
			return false;
		}

		return true;
	}
	
}
