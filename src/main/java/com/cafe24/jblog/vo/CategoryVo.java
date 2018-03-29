package com.cafe24.jblog.vo;

public class CategoryVo {
	private Long no;
	private Long blogNo;
	private String name;
	private String regDate;
	private String description;
	private Long postCount;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(Long blogNo) {
		this.blogNo = blogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPostCount() {
		return postCount;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", blogNo=" + blogNo + ", name=" + name + ", regDate=" + regDate
				+ ", description=" + description + ", postCount=" + postCount + "]";
	}
}
