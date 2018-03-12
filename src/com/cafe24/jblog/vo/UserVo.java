package com.cafe24.jblog.vo;

public class UserVo {
	private long no;
	private String id;
	private String password;
	private String name;
	
	public UserVo() {

	}
	public UserVo(long no, String id, String password, String name) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name
				+ "]";
	}
}
