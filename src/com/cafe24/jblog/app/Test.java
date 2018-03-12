package com.cafe24.jblog.app;

import com.cafe24.jblog.test.BlogDaoTest;
import com.cafe24.jblog.test.CategoryDaoTest;
import com.cafe24.jblog.test.CommentDaoTest;
import com.cafe24.jblog.test.PostDaoTest;
import com.cafe24.jblog.test.UserDaoTest;

public class Test {

	public static void main(String[] args) {
		insert();
		printList();
	}
	
	private static void insert() {
		UserDaoTest.insertTest();
		BlogDaoTest.insertTest();
		CategoryDaoTest.insertTest();
		PostDaoTest.insertTest();
		CommentDaoTest.insertTest();
	}
	
	private static void printList() {
		printSeperator("USER");
		UserDaoTest.getListTest();
		printSeperator();
		
		printSeperator("BLOG");
		BlogDaoTest.getListTest();
		printSeperator();
		
		printSeperator("CATEGORY");
		CategoryDaoTest.getListTest();
		printSeperator();
		
		printSeperator("POST");
		PostDaoTest.getListTest();
		printSeperator();
		
		printSeperator("COMMENT");
		CommentDaoTest.getListTest();
		printSeperator();
	}
	
	private static void printSeperator() {
		System.out.println("======================================");
	}
	
	private static void printSeperator(String name) {
		System.out.println("===============" + name + "===============");
	}
}
