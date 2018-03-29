package com.cafe24.jblog.vo;

public class CommentVo {
	private Long no;
	private Long postNo;
	private Long userNo;
	private String content;
	private String regDate;
	
	private String userName;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", postNo=" + postNo + ", userNo=" + userNo + ", content=" + content
				+ ", regDate=" + regDate + ", userName=" + userName + "]";
	}
}
