package com.ssafy.model.dto;

public class Food {
	private String name;
	private String company;
	private String materials;
	private String[] allergys;
	private String allergy;
	private int code;
	public Food() {};
	public Food(String name, String company, String materials) {
		this.name = name;
		this.company = company;
		this.materials = materials;
	}
	public Food(int code,String name, String company, String materials) {
		this.name = name;
		this.company = company;
		this.materials = materials;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String[] getAllergys() {
		return allergys;
	}
	public void setAllergys(String[] allergys) {
		this.allergys = allergys;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
}
