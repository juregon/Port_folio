package com.ssafy.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.vo.FoodNutrition;

public class FoodNutDao {
	private Connection conn;
	private static FoodNutDao instance;
	
	public FoodNutDao() {
		 conn = FoodDaoImpl.getConn();
	}
	
	public static FoodNutDao getInstance() {
		if(instance == null) {
			instance = new FoodNutDao();
		}
		return instance;
	}

	public void loadData() {
		try {
			 DocumentBuilder builder;
			 DocumentBuilderFactory factory;
			 Document document;
			 
			 factory = DocumentBuilderFactory.newInstance();
			 builder = factory.newDocumentBuilder();
			 document = builder.parse(new File("C:\\SSAFY\\work_java_third\\work_java_third\\SafeFood_Web_Back_End_Seoul_6_LeeJungyeon_KimJieun\\WebContent\\xml\\FoodNutritionInfo.xml"));
			 document.getDocumentElement().normalize();
			 
			 NodeList nodeList = document.getElementsByTagName("item");
			 for (int i = 0; i < nodeList.getLength(); i++) {
				
			
			 Node foodNode = nodeList.item(i);
			 Element foodElement = (Element)foodNode;
			 
			 String name = foodElement.getElementsByTagName("DESC_KOR").item(0).getTextContent();
			 String weight = foodElement.getElementsByTagName("SERVING_WT").item(0).getTextContent();
			 String cal = foodElement.getElementsByTagName("NUTR_CONT1").item(0).getTextContent();
			 String car = foodElement.getElementsByTagName("NUTR_CONT2").item(0).getTextContent();
			 String protein = foodElement.getElementsByTagName("NUTR_CONT3").item(0).getTextContent();
			 String sugar = foodElement.getElementsByTagName("NUTR_CONT4").item(0).getTextContent();
			 String glucose = foodElement.getElementsByTagName("NUTR_CONT5").item(0).getTextContent();
			 String salt = foodElement.getElementsByTagName("NUTR_CONT6").item(0).getTextContent();
			 String chole = foodElement.getElementsByTagName("NUTR_CONT7").item(0).getTextContent();
			 String fat = foodElement.getElementsByTagName("NUTR_CONT8").item(0).getTextContent();
			 String transfat = foodElement.getElementsByTagName("NUTR_CONT9").item(0).getTextContent();
			
			 insert(name, weight, cal, car, protein, sugar, glucose, salt, chole, fat, transfat);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	public FoodNutrition search(String name) {
		if(name.equals(""))return null;
		FoodNutrition fn = new FoodNutrition();
		String sql = "SELECT * FROM foodnut WHERE name=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fn.setName(rs.getString(1));
				fn.setWeight(rs.getString(2));
				fn.setCal(rs.getString(3));
				fn.setCar(rs.getString(4));
				fn.setProtein(rs.getString(5));
				fn.setSugar(rs.getString(6));
				fn.setGlucose(rs.getString(7));
				fn.setSalt(rs.getString(8));
				fn.setChole(rs.getString(9));
				fn.setFat(rs.getString(10));
				fn.setTransfat(rs.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return fn;
	}
	
	
	public void insert(String name, String weight, String cal, String car, String protein, String sugar, String glucose, String salt, String chole, String fat, String transfat) {
		String sql = "INSERT INTO foodnut VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, weight);
			pstmt.setString(3, cal);
			pstmt.setString(4, car);
			pstmt.setString(5, protein);
			pstmt.setString(6, sugar);
			pstmt.setString(7, glucose);
			pstmt.setString(8, salt);
			pstmt.setString(9, chole);
			pstmt.setString(10, fat);
			pstmt.setString(11, transfat);
			pstmt.executeUpdate(); // 삽입
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(pstmt);
		}
	}
	
	// close
		private void close(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		private void close(PreparedStatement pstmt) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	
}
