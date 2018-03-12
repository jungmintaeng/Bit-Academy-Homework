package com.cafe24.jblog.vo;

public class CategoryVo {
	private long no;
	private String name;
	private long blog_no;
	
	
	public CategoryVo() {
		
	}
	public CategoryVo(long no, String name, long blog_no) {
		super();
		this.no = no;
		this.name = name;
		this.blog_no = blog_no;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(long blog_no) {
		this.blog_no = blog_no;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", blog_no=" + blog_no + "]";
	}
}
