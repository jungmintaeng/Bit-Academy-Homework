package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String agreeProv = request.getParameter("agreeProv");
		
		if(! "y".equals(agreeProv)) {
			WebUtil.alert(request, response, "서비스 이용에 동의하셔야 합니다.", "/mysite/user?a=joinform");
			return;
		}
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		if(new UserDao().insert(vo)) {
			WebUtil.redirect(request, response, "/mysite/user?a=joinsuccess");
		} else {
			WebUtil.alert(request, response, "가입이 이루어지지 않았습니다. 이메일이 중복되었거나 부적절한 입력입니다.", "/mysite/user?a=joinform");
		}
	}
}
