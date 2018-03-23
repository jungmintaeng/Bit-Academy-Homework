package com.cafe24.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;

@Controller("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/ce")
	@ResponseBody
	public JSONResult checkEmail(
				@RequestParam(value="email", required=true, defaultValue="") String email
			) {
		return JSONResult.success(
				userService.isEmailExists(email) ? "exist" : "" );
	}
}
