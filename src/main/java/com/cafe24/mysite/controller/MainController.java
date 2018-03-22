package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value= {"/main", "/"})
	public String main() {
		String sql = "DELETE FROM COMMENT WHERE NO=?";
		System.out.println(sql.toLowerCase());
		return "main/index";
	}
}
