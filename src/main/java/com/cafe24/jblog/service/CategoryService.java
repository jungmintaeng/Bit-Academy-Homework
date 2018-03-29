package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryRepository;
import com.cafe24.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getCategoryList(Long no){
		List<CategoryVo> categories = categoryRepository.getList(no);
		CategoryVo vo = new CategoryVo();
		vo.setNo(0L);
		vo.setBlogNo(no);
		vo.setName("미지정");
		vo.setPostCount(categoryRepository.countNull(no));
		vo.setDescription("카테고리가 지정되지 않은 글들의 모임");
		categories.add(vo);
		return categories;
	}
	
	public boolean addCategory(CategoryVo vo) {
		return categoryRepository.insert(vo) == 1;
	}
	
	public boolean deleteCategory(Long no) {
		return categoryRepository.delete(no) == 1;
	}
}
