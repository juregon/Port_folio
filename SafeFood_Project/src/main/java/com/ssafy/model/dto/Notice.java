package com.ssafy.model.dto;

public class Notice {
	private int num;
	private String division;
	private String title;
	private String date;
	private String description;
	
	public Notice() {
	}
	
	public Notice(int num, String division, String title, String date, String description) {
		this.num = num;
		this.division = division;
		this.title = title;
		this.date = date;
		this.description = description;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String toString() {
		return "Notice [num=" + num + ", division=" + division + ", title=" + title + ", date=" + date
				+ ", description=" + description + "]";
	}
	
}
