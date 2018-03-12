package com.cafe24.jblog.test;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.dao.CategoryDao;
import com.cafe24.jblog.vo.CategoryVo;

public class CategoryDaoTest {
	public static void main(String[] args) {
		insertTest();
		getListTest();
	}
	
	public static void getListTest() {
		CategoryDao dao = new CategoryDao();
		List<CategoryVo> vos = new ArrayList<>();
		vos = dao.getList();
		for(CategoryVo vo : vos)
			System.out.println(vo);
	}
	
	public static void insertTest() {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();
		vo.setNo(1);
		vo.setName("JDBC");
		vo.setBlog_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(2);
		vo.setName("재미있는 일상");
		vo.setBlog_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(3);
		vo.setName("취업 도전기");
		vo.setBlog_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
	}
}
