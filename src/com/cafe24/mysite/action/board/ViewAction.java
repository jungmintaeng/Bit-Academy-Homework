package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.dao.CommentDao;

public class ViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		BoardDao dao = new BoardDao();
		CommentDao cDao = new CommentDao();
		request.setAttribute("article", dao.get(target));
		request.setAttribute("comment_list", cDao.getList(target));
		dao.updateHits(target);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}
}
