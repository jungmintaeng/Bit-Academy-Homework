package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;

	@RequestMapping( {"", "/"} )
	public String index(
			Model model
	) {
		model.addAttribute("newList", mainService.getNewProductList());
		model.addAttribute("hitList", mainService.getHitProductList());
		model.addAttribute("discountList", mainService.getDiscountList());
		return "main/index";
	}

	@RequestMapping("/init")
	public String init(){
		mainService.init();
		return "redirect:/";
	}
}
