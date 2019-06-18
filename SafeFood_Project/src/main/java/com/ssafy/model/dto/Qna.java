package com.ssafy.model.dto;

public class Qna {
	private int num;
	private String title;
	private String id;
	private String date;
	private String description;
	
	public Qna() {
	}

	public Qna(int num, String title, String id, String date, String description) {
		this.num = num;
		this.title = title;
		this.id = id;
		this.date = date;
		this.description = description;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
