package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.vo.CategoryVo;

@Controller("categoryController")
@RequestMapping("/{userID}/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{blogNo}", produces = "application/json")
	@ResponseBody
	public JSONResult list(
				@PathVariable Long blogNo
			) {
		return JSONResult.success(categoryService.getCategoryList(blogNo));
	}
	
	@RequestMapping(value="/add/{blogNo}", produces = "application/json")
	@ResponseBody
	public JSONResult add(
				@RequestParam("n") String categoryName,
				@RequestParam("d") String description,
				@PathVariable Long blogNo
			) {
		CategoryVo vo = new CategoryVo();
		vo.setName(categoryName);
		vo.setDescription(description);
		vo.setBlogNo(blogNo);
		return JSONResult.success(categoryService.addCategory(vo));
	}
	
	@RequestMapping(value="/delete/{categoryNo}", produces = "application/json")
	@ResponseBody
	public JSONResult delete(
				@PathVariable Long categoryNo
			) {
		if(categoryNo == 0) {
			return JSONResult.success(false);
		}
		return JSONResult.success(categoryService.deleteCategory(categoryNo));
	}
}
