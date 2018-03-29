package com.cafe24.jblog.vo;

public class BlogVo {
	private Long no;
	private Long userNo;
	private String title;
	private Long logoNo;
	private String uploadName;
	private String saveName;
	private String regDate;
	private String userID;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getLogoNo() {
		return logoNo;
	}
	public void setLogoNo(Long logoNo) {
		this.logoNo = logoNo;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", logoNo=" + logoNo + ", uploadName="
				+ uploadName + ", saveName=" + saveName + ", regDate=" + regDate + ", userID=" + userID + "]";
	}
}
