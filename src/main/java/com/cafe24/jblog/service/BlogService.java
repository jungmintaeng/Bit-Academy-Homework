package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogRepository;
import com.cafe24.jblog.repository.CategoryRepository;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public BlogVo getBlogByUserID(String userID) {
		return blogRepository.get(userID);
	}
	
	public boolean updateBlogInfo(BlogVo vo) {
		return blogRepository.update(vo) == 1;
	}
	
	public List<CategoryVo> getBlogCategories(Long no){
		return categoryRepository.getList(no);
	}
	
	public boolean updateLogo(BlogVo vo) {
		if(vo.getSaveName() == null) {
			return false;
		}
		return blogRepository.updateLogo(vo) == 1;
	}
}
