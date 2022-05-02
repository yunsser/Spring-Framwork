package com.tjoeun.vo;

import java.util.ArrayList;
import java.util.List;

public class BoardVO 
{
	private int num;
	private String title;
	private String author;
	private String contents;
	private java.sql.Date bdate;
	public List<AttachVO> attach = new ArrayList<>();
	
//	============================================================================================

	public BoardVO() {}

	public BoardVO(int num) { // 글번호만 있어도 객체생성 가능
		this.setNum(num);
	}
	
//	============================================================================================

	@Override
	public String toString() {
		return num + " " + title + " " + author + " " + contents + " " + bdate;
	}
	
	@Override
	public boolean equals(Object obj) { // 글번호가 같으면 같은 글이라고 인정
		BoardVO other = (BoardVO) obj;
		return this.num==other.num;
	}
	
//	============================================================================================

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Date getBdate() {
		return bdate;
	}
	public void setBdate(java.sql.Date bdate) {
		this.bdate = bdate;
	}
	public List<AttachVO> getAttach() {
		return attach;
	}
}
