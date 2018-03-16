package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

public class ModifyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long target = WebUtil.checkParameter(request.getParameter("target"), 0L);
		String title = WebUtil.checkParameter(request.getParameter("title"), "");
		String content = WebUtil.checkParameter(request.getParameter("content"), "");
		
		if(target == 0L) {
			WebUtil.alert(request, response, "해당 글을 찾을 수 없습니다.", "/mysite/board");
			return;
		}
		
		if(title == null || content == null || "".equals(title) || "".equals(content)) {
			WebUtil.alert(request, response, "제목 또는 내용을 입력하세요.", "/mysite/board?a=view&target=" + String.valueOf(target));
			return;
		}
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setNo(target);
		vo.setContent(content);
		vo.setTitle(title);
		
		dao.update(vo);
		
		WebUtil.alert(request, response, "수정되었습니다.", "/mysite/board");
	}
}
