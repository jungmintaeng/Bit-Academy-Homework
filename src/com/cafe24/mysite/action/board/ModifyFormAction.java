package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

public class ModifyFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		if(target == 0L) {
			WebUtil.alert(request, response, "해당 글을 찾을 수 없습니다.", "/mysite/board");
		}
		BoardDao boardDao = new BoardDao();
		BoardVo original = boardDao.get(target);
		request.setAttribute("original", original);
		WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}
	
}
