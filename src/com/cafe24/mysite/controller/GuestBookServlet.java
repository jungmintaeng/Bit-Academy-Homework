package com.cafe24.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mysite.action.guestbook.GuestBookActionFactory;

@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public GuestBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		Action action = new GuestBookActionFactory().getAction(actionName);
		action.execute(request, response);
		
		/* // 고치기 전 소스 - Servlet 클래스에서 액션을 분류하는 일, 액션을 수행하는 일 두 가지를 하고 있다.
		if("deleteform".equals(action)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/deleteform.jsp");
			
		} else if ("add".equals(action)) {
		
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			if(name == null || password == null || content == null){
				response.getWriter().print(
						"<script type='text/javascript'>" + 
						"		alert('잘못된 입력값입니다.');" + 
						"	location.href='/guestbook2/gb?a=list';" +
						"</script>");
				return;
			}
			GuestBookVo vo = new GuestBookVo();
			GuestBookDao dao = new GuestBookDao();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			dao.insert(vo);
			response.sendRedirect("/guestbook2/gb?a=list");
			
		} else if ("delete".equals(action)) {
			
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
				response.getWriter().print(
						"<script type='text/javascript'>" + 
						"		alert('비밀번호가 일치하지 않습니다.');" + 
					    "	location.href='/guestbook2/gb?a=list';" +
						"</script>");
				return;
			}else{
				dao.delete(deleteNo);
				response.sendRedirect("/guestbook2/gb?a=list");
			}
			
		} else {	//index.jsp	//
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> vos = dao.getList();
			request.setAttribute("list", vos);
			
			WebUtil.forward(request, response, "/WEB-INF/views/index.jsp");
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
