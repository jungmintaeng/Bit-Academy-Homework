package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class ModifyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.alert(request, response, "잘못된 접근입니다.", "/mysite/main");
		}
		String name = WebUtil.checkParameter(request.getParameter("name"), authUser.getName());
		String password = WebUtil.checkParameter(request.getParameter("password"), "");
		String gender = WebUtil.checkParameter(request.getParameter("gender"), "");

		if("".equals(gender)) {
			WebUtil.alert(request, response, "수정하지 못했습니다. 정보를 확인해주세요.", "/mysite/main");
			return;
		}
		
		UserDao dao = new UserDao();
		UserVo updateVo = new UserVo();
		updateVo.setNo(authUser.getNo());
		updateVo.setName(name);
		updateVo.setPassword(password);
		updateVo.setGender(gender);
		
		if(dao.update(updateVo)) {
			WebUtil.alert(request, response, "회원정보를 수정하였습니다.", "/mysite/main");
		} else {
			WebUtil.alert(request, response, "수정하지 못했습니다. 정보를 확인해주세요.", "/mysite/main");
		}
	}
}
