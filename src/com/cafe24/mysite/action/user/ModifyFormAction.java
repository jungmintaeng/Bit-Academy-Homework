package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class ModifyFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.alert(request, response, "잘못된 접근입니다.", "/mysite/main");
			return;
		}
		UserDao dao = new UserDao();
		UserVo authUserInfoVo = dao.get(authUser.getNo());
		request.setAttribute("authUserInfo", authUserInfoVo);
		WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
	}
}
