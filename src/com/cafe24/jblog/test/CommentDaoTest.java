package com.cafe24.jblog.test;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.dao.CommentDao;
import com.cafe24.jblog.vo.CommentVo;

public class CommentDaoTest {
	public static void main(String[] args) {
		insertTest();
		getListTest();
	}
	
	public static void getListTest() {
		CommentDao dao = new CommentDao();
		List<CommentVo> vos = new ArrayList<>();
		vos = dao.getList();
		for(CommentVo vo : vos)
			System.out.println(vo);
	}
	
	public static void insertTest() {
		CommentDao dao = new CommentDao();
		CommentVo vo = new CommentVo();
		
		vo.setNo(1);
		vo.setUser_no(1);
		vo.setPost_no(1);
		vo.setContent("정말 재미있는 글이었어요");
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(2);
		vo.setUser_no(1);
		vo.setPost_no(1);
		vo.setContent("더럽게도 재미없는 글이네요");
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(3);
		vo.setUser_no(1);
		vo.setPost_no(2);
		vo.setContent("극혐");
		if(dao.insert(vo))
			System.out.println("inserted");
		
		vo.setNo(4);
		vo.setUser_no(1);
		vo.setPost_no(2);
		vo.setContent("좋아요^_^");
		if(dao.insert(vo))
			System.out.println("inserted");
	}
}
