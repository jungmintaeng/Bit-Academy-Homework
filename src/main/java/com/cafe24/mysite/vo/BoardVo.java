package com.cafe24.mysite.vo;

public class BoardVo {
	private Long no;
	private Long writer_no;
	private String writer_name;
	private String title;
	private String content;
	private Long hits;
	private String reg_date;
	private Long group_no;
	private Long order_no;
	private Long depth;
	
	public BoardVo() {
		super();
	}
	
	public BoardVo(Long no, Long writer_no, String title, String content, Long hits, Long group_no,
			Long order_no, Long depth) {
		this.no = no;
		this.writer_no = writer_no;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getWriter_no() {
		return writer_no;
	}
	public void setWriter_no(Long writer_no) {
		this.writer_no = writer_no;
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
	public Long getHits() {
		return hits;
	}
	public void setHits(Long hits) {
		this.hits = hits;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Long group_no) {
		this.group_no = group_no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}	
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", writer_no=" + writer_no + ", writer_name=" + writer_name + ", title=" + title
				+ ", content=" + content + ", hits=" + hits + ", reg_date=" + reg_date + ", group_no=" + group_no
				+ ", order_no=" + order_no + ", depth=" + depth + "]";
	}
}
