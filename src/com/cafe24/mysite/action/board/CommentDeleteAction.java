package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.CommentDao;

public class CommentDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long article_no = WebUtil.checkParameter(request.getParameter("article_no"), 0L);
		long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		
		String redirectUrl = "/mysite/board?a=view&target=" + article_no;
		
		if(article_no == 0L) {
			WebUtil.alert(request, response, "접근 권한이 없습니다.", redirectUrl);
			return;
		}
		
		if(target == 0L) {
			WebUtil.alert(request, response, "해당 댓글을 지울 수 없습니다.", redirectUrl);
			return;
		}
		
		CommentDao dao = new CommentDao();
		dao.delete(target);
		
		WebUtil.redirect(request, response, redirectUrl);
	}

}
