package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if("".equals(email) || "".equals(password)) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		// userService 주입하기 위해 UserService를 뽑는 일
		ApplicationContext ac = 
				WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService service = ac.getBean(UserService.class);
		UserVo authUser = service.getUser(vo);
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() + "/main");
		return false;
	}
}
