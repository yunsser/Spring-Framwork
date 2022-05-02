package com.tjoeun.vo;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component // 자동으로... 넣어줌... 스프링이... // 앱을 구성하는 부품
public class User 
{
	private String uid;
	private String pwd;
	private String name;
	private String phone;
	/*
	private String title;
	private String content;
	private java.sql.Date date;
	*/
	
	//--------------------------------------------------------------
	
	
	public String getLine() 
	{
		return String.format("%s|%s|%s|%s", uid, pwd, name, phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.uid);
	}

	@Override
	public boolean equals(Object obj) {
		User u =(User) obj;
		return this.uid.equals(u.getUid());
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s", uid, pwd, name, phone);
	}
	
	//--------------------------------------------------------------

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	//--------------------------------------------------------------
/*
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	*/
	
}
