package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.Page;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long req_page = WebUtil.checkParameter(request.getParameter("page"), 0L);
		String kwd = WebUtil.checkParameter(request.getParameter("kwd"), "");
		Page page = new Page(req_page, kwd);
		if(! page.isCurPageValid()) {
			page = new Page(1, kwd);
		}
		request.setAttribute("pageObj", page);
		request.setAttribute("list", new BoardDao().getList(page.getCurPage(), kwd));
		request.setAttribute("kwd", kwd);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
}