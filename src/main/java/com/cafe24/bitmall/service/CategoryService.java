package com.cafe24.bitmall.service;

import com.cafe24.bitmall.repository.CategoryRepository;
import com.cafe24.bitmall.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryVo> getList(){
        return categoryRepository.getList();
    }

    public CategoryVo getByNo(Long no){ return categoryRepository.getByNo(no); }
}
