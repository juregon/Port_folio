package com.ssafy.vo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FoodNutrition {
	private String name;
	private String weight;
	private String cal;
	private String car;
	private String protein;
	private String sugar;
	private String glucose;
	private String salt;
	private String chole;
	private String fat;
	private String transfat;
	
	public FoodNutrition() {
	}
	public FoodNutrition(String name, String weight, String cal, String car, String protein, String sugar,
			String glucose, String salt, String chole, String fat, String transfat) {
		this.name = name;
		this.weight = weight;
		this.cal = cal;
		this.car = car;
		this.protein = protein;
		this.sugar = sugar;
		this.glucose = glucose;
		this.salt = salt;
		this.chole = chole;
		this.fat = fat;
		this.transfat = transfat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCal() {
		return cal;
	}
	public void setCal(String cal) {
		this.cal = cal;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getSugar() {
		return sugar;
	}
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}
	public String getGlucose() {
		return glucose;
	}
	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getChole() {
		return chole;
	}
	public void setChole(String chole) {
		this.chole = chole;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	}
	public String getTransfat() {
		return transfat;
	}
	public void setTransfat(String transfat) {
		this.transfat = transfat;
	}
	


}
