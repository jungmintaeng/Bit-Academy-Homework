package com.cafe24.jblog.vo;

public class PostVo {
	private long no;
	private String title;
	private String content;
	private String reg_date;
	private long category_no;
	private long blog_no;
	
	
	public PostVo() {

	}
	public PostVo(long no, String title, String content, String reg_date, long category_no, long blog_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.category_no = category_no;
		this.blog_no = blog_no;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(long category_no) {
		this.category_no = category_no;
	}
	public long getBlog_no() {
		return blog_no;
	}
	public void setBlog_no(long blog_no) {
		this.blog_no = blog_no;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", category_no=" + category_no + ", blog_no=" + blog_no + "]";
	}
}
