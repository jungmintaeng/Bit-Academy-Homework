package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Auth
@Controller
@RequestMapping(value="/{userID}/admin")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String basic(
				@AuthUser UserVo authUser,
				@PathVariable String userID, 
				Model model
			) {
		if(authUser == null) {
			return "redirect:/" + userID;
		}
		BlogVo blog = blogService.getBlogByUserID(userID);
		model.addAttribute("blog", blog);
		model.addAttribute("logoURL", blog.getSaveName() == null ? 
						null : FileUploadService.getLogoRealPath(blog.getSaveName()));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/basic", method=RequestMethod.POST)
	public String basic(
				@AuthUser UserVo authUser,
				@PathVariable String userID,
				@ModelAttribute BlogVo vo,
				@RequestParam("logo-file") MultipartFile logo,
				Model model
			) {
		if(authUser == null) {
			return "redirect:/" + userID;
		}
		if("".equals(logo.getOriginalFilename()) == false) {
			fileUploadService.restore(logo, vo);
			blogService.updateLogo(vo);
		}
		blogService.updateBlogInfo(vo);
		return "redirect:/" + userID;
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String category(
				@AuthUser UserVo authUser,
				@PathVariable String userID, 
				Model model
			) {
		if(authUser == null) {
			return "redirect:/" + userID;
		}
		BlogVo blog = blogService.getBlogByUserID(userID);
		model.addAttribute("blog", blog);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(
				@AuthUser UserVo authUser,
				@PathVariable String userID,
				Model model
			) {
		if(authUser == null) {
			return "redirect:/" + userID;
		}
		BlogVo blog = blogService.getBlogByUserID(userID);
		model.addAttribute("blog", blog);
		model.addAttribute("categoryList",categoryService.getCategoryList(blog.getNo()));
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST )
	public String write(
				@AuthUser UserVo authUser,
				@ModelAttribute PostVo vo,
				@PathVariable String userID,
				Model model
			) {
		if(authUser == null) {
			return "redirect:/" + userID;
		}
		postService.addPost(vo);
		return "redirect:/" + userID;
	}
}
