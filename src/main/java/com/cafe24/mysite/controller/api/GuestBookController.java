package com.cafe24.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.repository.GuestBookDao;

@Controller("guestBookAPIController")
@RequestMapping("/api/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONResult list() {
		return JSONResult.success(guestBookDao.getList());
	}
}
