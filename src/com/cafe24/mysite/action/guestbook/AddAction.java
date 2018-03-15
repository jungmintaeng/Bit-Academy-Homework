package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String content = request.getParameter("content");
		if(name == null || password == null || content == null || "".equals(name) || "".equals(password) || "".equals(content)){
			WebUtil.alert(request, response, "잘못된 입력값입니다. 다시 입력하세요", "/mysite/guestbook?a=list");
			return;
		}
		GuestBookVo vo = new GuestBookVo();
		GuestBookDao dao = new GuestBookDao();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContent(content);
		dao.insert(vo);
		response.sendRedirect("/mysite/guestbook?a=list");
	}
	
}
