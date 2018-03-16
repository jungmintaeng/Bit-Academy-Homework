package com.cafe24.mysite.vo;

public class BoardVo {
	private long no;
	private long writer_no;
	private String writer_name;
	private String title;
	private String content;
	private long hits;
	private String reg_date;
	private long group_no;
	private long order_no;
	private long depth;
	
	public BoardVo() {
		super();
	}
	
	public BoardVo(long no, long writer_no, String title, String content, long hits, long group_no,
			long order_no, long depth) {
		this.no = no;
		this.writer_no = writer_no;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getWriter_no() {
		return writer_no;
	}
	public void setWriter_no(long writer_no) {
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
	public long getHits() {
		return hits;
	}
	public void setHits(long hits) {
		this.hits = hits;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public long getGroup_no() {
		return group_no;
	}
	public void setGroup_no(long group_no) {
		this.group_no = group_no;
	}
	public long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(long order_no) {
		this.order_no = order_no;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
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
