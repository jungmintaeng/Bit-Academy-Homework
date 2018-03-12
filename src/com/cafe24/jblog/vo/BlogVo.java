package com.cafe24.jblog.vo;

public class BlogVo {
	private long no;
	private String title;
	private String imageUrl;
	private long manager_no;

	public BlogVo() {
		
	}
	public BlogVo(long no, String title, String imageUrl, long manager_no) {
		super();
		this.no = no;
		this.title = title;
		this.imageUrl = imageUrl;
		this.manager_no = manager_no;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public long getManager_no() {
		return manager_no;
	}
	public void setManager_no(long manager_no) {
		this.manager_no = manager_no;
	}
	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", title=" + title + ", imageUrl=" + imageUrl + ", manager_no=" + manager_no + "]";
	}
}
