package com.cafe24.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.PostVo;

@Controller
public class MainController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/")
	public String main() {
		return "main/index";
	}

	@RequestMapping(value = "/{userID}")
	public String blog(
				@PathVariable("userID") String userID, 
				Model model,
				@RequestParam(value="c", required=true, defaultValue="0") Long categoryNo ,
				@RequestParam(value="p", required=true, defaultValue="0") Long postNo
			) {
		BlogVo blog = blogService.getBlogByUserID(userID);
		if(blog == null) {
			return "redirect:/";
		}
		model.addAttribute("blog", blog);
		model.addAttribute("logoURL", blog.getSaveName() == null ? 
				null : FileUploadService.getLogoRealPath(blog.getSaveName()));
		model.addAttribute("categoryList", categoryService.getCategoryList(blog.getNo()));
		List<PostVo> posts = categoryNo != 0 ?
												postService.getPostList(blog.getNo(), categoryNo) :
												postService.getPostList(blog.getNo());
		model.addAttribute("postList", posts);
		PostVo post = postNo == 0 && posts.size() >  0 ?
										posts.get(0) :
										postService.getPostByNo(postNo);
		model.addAttribute("post", post);
		model.addAttribute("c", categoryNo);
		model.addAttribute("p", postNo);
		return "blog/blog-main";
	}
}
