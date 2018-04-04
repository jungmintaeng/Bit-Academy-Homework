package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	@RequestMapping(value="")
	public String gallery() {
		return "gallery/index";
	}
}
