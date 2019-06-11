package com.ssafy.vo;

import java.util.ArrayList;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String[] allergys;
	
	

	public Member() {
	}

	public Member(String id, String pw, String name, String address, String phone, String email, String[] allergys) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.allergys = allergys;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getAllergys() {
		return allergys;
	}

	public void setAllergys(String[] allergys) {
		this.allergys = allergys;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
