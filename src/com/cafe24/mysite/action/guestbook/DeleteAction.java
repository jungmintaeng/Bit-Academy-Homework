package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

public class DeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long deleteNo = 0L;
		request.setCharacterEncoding("utf-8");
		String password = request.getParameter("password");
		String no = request.getParameter("target");
		try{
			deleteNo = Long.parseLong(no);
		}catch(Exception e){
			e.printStackTrace();
		}
		GuestBookDao dao = new GuestBookDao();
		GuestBookVo vo = dao.getRowByNo(deleteNo);
		if(password == null || !password.equals(vo.getPassword())){
			response.setCharacterEncoding("euc-kr");
			WebUtil.alert(request, response, "비밀번호가 일치하지 않습니다.", "/mysite/guestbook?a=list");
			return;
		}else{
			dao.delete(deleteNo);
			response.sendRedirect("/mysite/guestbook?a=list");
		}
	}
	
}
