package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

public class ReplyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title, content;
		long parent = WebUtil.checkParameter(request.getParameter("parent"), 0L);
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		
		if(authUser == null) {
			WebUtil.alert(request, response, "잘못된 접근경로입니다.", "/mysite/board");
			return;
		}
		
		if(parent == 0L) {
			WebUtil.alert(request, response, "답글을 작성할 수 없습니다.", "/mysite/board");
			return;
		}
		
		long writer_no = authUser.getNo();
		
		writer_no = authUser.getNo();
		title = WebUtil.checkParameter(request.getParameter("title"), "");
		content = WebUtil.checkParameter(request.getParameter("content"), "");
		
		if(title == null || content == null || "".equals(title) || "".equals(content)) {
			WebUtil.alert(request, response, "제목 또는 내용을 입력하세요.", "/mysite/board?a=writeform");
			return;
		}
		//NO, WRITER_NO, TITLE, CONTENT, HITS, REG_DATE, GROUP_NO, ORDER_NO, DEPTH
		BoardVo vo = new BoardVo();
		vo.setWriter_no(writer_no);
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardDao dao = new BoardDao();
		dao.insertReply(vo, parent);
		
		WebUtil.redirect(request, response, "/mysite/board");
	}
	
}
