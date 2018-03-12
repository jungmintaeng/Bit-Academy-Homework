package com.cafe24.jblog.test;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.dao.PostDao;
import com.cafe24.jblog.vo.PostVo;

public class PostDaoTest {
	public static void main(String[] args) {
		insertTest();
		getListTest();
	}
	
	public static void getListTest() {
		PostDao dao = new PostDao();
		List<PostVo> vos = new ArrayList<>();
		vos = dao.getList();
		for(PostVo vo : vos)
			System.out.println(vo);
	}
	
	public static void insertTest() {
		PostDao dao = new PostDao();
		PostVo vo = new PostVo();
		
		vo.setNo(1);
		vo.setTitle("JDBC란?");
		vo.setContent("JDBC(Java Database Connectivity)란 데이터베이스에 접근하여 "
				+ "SQL문을 실행하기 위한 자바 라이브러리를 말한다.");
		vo.setCategory_no(1);
		vo.setBlog_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(2);
		vo.setTitle("JDBC 설치하기");
		vo.setContent("알아서 잘 설치하시길 바랍니다.");
		vo.setCategory_no(1);
		vo.setBlog_no(1);
		if(dao.insert(vo))
			System.out.println("inserted");
	}
}
