package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestBookService;
import com.cafe24.mysite.vo.GuestBookVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		model.addAttribute("list", guestBookService.getAllGuestBooks());
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add")
	public String add(GuestBookVo vo) {
		guestBookService.addGuestBook(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete() {
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo) {
		guestBookService.deleleteGuestBook(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public String ajax() {
		return "guestbook/index-ajax";
	}
}