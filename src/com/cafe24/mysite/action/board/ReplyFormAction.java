package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;

public class ReplyFormAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		if(target == 0L) {
			WebUtil.alert(request, response, "답글을 작성할 수 없습니다.", "/mysite/board");
			return;
		}
		request.setAttribute("article", new BoardDao().get(target));
		request.setAttribute("target", target);
		WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
	}
}
