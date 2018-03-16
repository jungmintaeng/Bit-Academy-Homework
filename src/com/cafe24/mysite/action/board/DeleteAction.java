package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;

public class DeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		BoardDao dao = new BoardDao();
		if(target == 0L) {
			WebUtil.alert(request, response, "삭제할 수 없습니다.", "/mysite/board");
			return;
		}
		dao.delete(target);
		WebUtil.alert(request, response, "글이 삭제되었습니다.", "/mysite/board");
	}
}
