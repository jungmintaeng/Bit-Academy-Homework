package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;

public class CheckEmailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = WebUtil.checkParameter(request.getParameter("email"), "");
		if("".equals(email)) {
			WebUtil.selfClose(request, response, "Email을 입력하세요.");
			return;
		}
		
		UserDao dao = new UserDao();
		
		if(dao.isEmailExists(email)) {
			WebUtil.selfClose(request, response, "이미 사용중인 이메일입니다.");
			return;
		}else {
			WebUtil.selfClose(request, response, "사용 가능한 이메일입니다.");
			return;
		}
	}

}
