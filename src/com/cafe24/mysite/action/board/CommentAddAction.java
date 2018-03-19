package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.CommentDao;
import com.cafe24.mysite.vo.CommentVo;

public class CommentAddAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = WebUtil.checkParameter(request.getParameter("comment_content"), "");
		long writer_no = WebUtil.checkParameter(request.getParameter("writer_no"), 0L);
		long article_no = WebUtil.checkParameter(request.getParameter("target"), 0L);
		
		String redirectUrl = "/mysite/board?a=view&target=" + article_no;
		
		if(writer_no == 0L) {
			WebUtil.alert(request, response, "접근 권한이 없습니다.", redirectUrl);
			return;
		}
		
		if(article_no == 0L) {
			WebUtil.alert(request, response, "작성할 수 없습니다.", redirectUrl);
			return;
		}
		
		if("".equals(content)) {
			WebUtil.alert(request, response, "내용을 입력하세요.", redirectUrl);
			return;
		}
		
		CommentVo vo = new CommentVo();
		vo.setContent(content);
		vo.setWriter_no(writer_no);
		vo.setArticle_no(article_no);
		
		CommentDao dao = new CommentDao();
		dao.insert(vo);
		
		WebUtil.redirect(request, response, redirectUrl);
	}
}
