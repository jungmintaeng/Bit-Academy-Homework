package com.cafe24.security;

import com.cafe24.bitmall.service.UserService;
import com.cafe24.bitmall.vo.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String failPath = request.getContextPath() + "/user/login";
		String successPath = request.getContextPath() + "/";
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		//admin login인지 user login인지 구분
		if("admin".equals(role)){
			failPath = request.getContextPath() + "/user/login/admin";
			successPath = request.getContextPath() + "/admin/main";
		}else if("user".equals(role) == false){
			response.sendRedirect(failPath);
			return false;
		}
		if("".equals(id) || "".equals(password)) {
			response.sendRedirect(failPath);
			return false;
		}
		
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		vo.setRole(role);
		// userService 주입하기 위해 UserService를 뽑는 일
		ApplicationContext ac =
				WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService service = ac.getBean(UserService.class);
		UserVo authUser = service.getUser(vo);
		if(authUser == null) {
			response.sendRedirect(failPath);
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		if("admin".equals(authUser.getRole())){
			response.sendRedirect(successPath);
			return false;
		}
		response.sendRedirect(successPath);
		return false;
	}
}
