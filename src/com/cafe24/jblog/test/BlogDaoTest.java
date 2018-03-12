package com.cafe24.jblog.test;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.dao.BlogDao;
import com.cafe24.jblog.vo.BlogVo;

public class BlogDaoTest {
	public static void main(String[] args) {
		insertTest();
		getListTest();
	}
	
	public static void getListTest() {
		BlogDao dao = new BlogDao();
		List<BlogVo> vos = new ArrayList<>();
		vos = dao.getList();
		for(BlogVo vo : vos)
			System.out.println(vo);
	}
	
	public static void insertTest() {
		BlogDao dao = new BlogDao();
		BlogVo vo = new BlogVo();
		vo.setNo(1);
		vo.setTitle("정민의 JDBC 탐험");
		vo.setImageUrl("http://jungmin.com/jdbc/logo.jpg");
		vo.setManager_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
	}
}
