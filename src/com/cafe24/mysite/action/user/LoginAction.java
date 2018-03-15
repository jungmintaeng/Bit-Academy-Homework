package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserVo authUser = new UserDao().get(email, password);
		
		request.setAttribute("email", email);
		if(authUser == null) {
			request.setAttribute("loginResult", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		request.removeAttribute("loginResult");
		/**
		 * 인증된 유저 처리
		 */
		//true이면 세션 정보를 만들어서 던져줌
		request.getSession(true).setAttribute("authUser", authUser);
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}
}
