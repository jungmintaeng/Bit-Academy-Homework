package com.cafe24.jblog.vo;

public class CommentVo {
	private long no;
	private long user_no;
	private long post_no;
	private String content;
	private String reg_date;
	
	public CommentVo() {

	}
	public CommentVo(long no, long user_no, long post_no, String content, String reg_date) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.post_no = post_no;
		this.content = content;
		this.reg_date = reg_date;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public long getPost_no() {
		return post_no;
	}
	public void setPost_no(long post_no) {
		this.post_no = post_no;
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
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", user_no=" + user_no + ", post_no=" + post_no + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}
}
