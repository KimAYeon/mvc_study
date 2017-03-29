package com.spring.user.domain;

import java.util.Date;

public class UserVO {
	
	private String uid;
	private String upw;
	private String uname;
	private int urole;
	private Date regdate;
	private Date moddate;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUrole() {
		return urole;
	}
	public void setUrole(int urole) {
		this.urole = urole;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", urole=" + urole + ", regdate=" + regdate
				+ ", moddate=" + moddate + "]";
	}
	
}
