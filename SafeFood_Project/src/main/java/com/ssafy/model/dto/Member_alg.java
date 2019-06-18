package com.ssafy.model.dto;

import java.util.Arrays;

public class Member_alg {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String allergy;
	
	public Member_alg() {
	}
	public Member_alg(String id, String pw, String name, String address, String phone, String email, String allergy) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.allergy = allergy;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	@Override
	public String toString() {
		return "Member_alg [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", allergy=" + allergy + "]";
	}

	

}
