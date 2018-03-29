package com.cafe24.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 핸들러 종류 확인
		if ((handler instanceof HandlerMethod) == false) {
			return true;
		}

		// casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		Method method = handlerMethod.getMethod();
		if (method.getDeclaringClass().isAnnotationPresent(Auth.class)) {

		} else if(handlerMethod.getMethodAnnotation(Auth.class) == null){
			return true;
		}
		/**
		 * auth.role()을 사용하고 싶은 경우 auth.role() 값을 이용하면 된다.
		 */

		// @Auth가 붙어있는 경우
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		String[] paths = request.getServletPath().split("/");
		
		if(paths.length < 2) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		UserVo vo = (UserVo) session.getAttribute("authUser");
		if (vo == null || vo.getId().equals(paths[1].trim()) == false) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}

		return true;
	}
}
