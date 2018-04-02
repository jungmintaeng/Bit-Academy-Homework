package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

/**
 * 
 * 클래스 단위로 @Auth 붙여서 그에 맞는 Auth를 구현하는 과제
 * 
 * @Auth에서 Target을 METHOD, CLASS로 수정한다. Auth 내에서 클래스 부분도 확인해야 함
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo,
						Model model
			) {
		return "user/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, 
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			// 에러 출력
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				System.out.println(" ObjectError : " + e);
			}
			model.addAllAttributes(result.getModel());
			return "user/joinform";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/loginform";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVo vo, Model model, HttpServletRequest request) {
		UserVo authUser = userService.getUser(vo);
		if (authUser == null) {
			System.out.println("fail");
			model.addAttribute("result", "fail");
			return "user/loginform";
		}
		request.getSession().setAttribute("authUser", authUser);
		return "redirect:/main";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		System.out.println("request handler method [modify] : authUser --> \n" + authUser);
		if (authUser == null) {
			return "redirect:/main";
		}
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("authUserInfo", vo);
		return "user/modifyform";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo vo) {
		userService.updateUserInfo(vo);
		return "redirect:/main";
	}

	/*
	 * @ExceptionHandler( UserDaoException.class ) public String
	 * handleUserDaoException() { //TODO : 로그 남기기 return "error/error"; }
	 */
}
