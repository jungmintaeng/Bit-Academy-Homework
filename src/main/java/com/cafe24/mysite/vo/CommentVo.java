package com.cafe24.mysite.vo;

public class CommentVo {
	private Long no;
	private Long article_no;
	private Long writer_no;
	private String writer_name;
	private String reg_date;
	private String content;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Long article_no) {
		this.article_no = article_no;
	}
	public Long getWriter_no() {
		return writer_no;
	}
	public void setWriter_no(Long writer_no) {
		this.writer_no = writer_no;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", article_no=" + article_no + ", writer_no=" + writer_no + ", writer_name="
				+ writer_name + ", reg_date=" + reg_date + ", content=" + content + "]";
	}
}
