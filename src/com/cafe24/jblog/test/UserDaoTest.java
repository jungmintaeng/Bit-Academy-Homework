package com.cafe24.jblog.test;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.dao.UserDao;
import com.cafe24.jblog.vo.UserVo;

public class UserDaoTest {
	public static void main(String[] args) {
		insertTest();
		getListTest();
	}
	
	public static void getListTest() {
		UserDao dao = new UserDao();
		List<UserVo> vos = new ArrayList<>();
		vos = dao.getList();
		for(UserVo vo : vos)
			System.out.println(vo);
	}
	
	public static void insertTest() {
		UserDao dao = new UserDao();
		UserVo vo = new UserVo();
		vo.setNo(1);
		vo.setId("jungmin");
		vo.setPassword("1234");
		vo.setName("신정민");
		if(dao.insert(vo))
			System.out.println("inserted");
	}
}
