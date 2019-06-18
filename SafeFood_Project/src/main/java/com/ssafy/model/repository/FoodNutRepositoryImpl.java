package com.ssafy.model.repository;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.model.dto.FoodNutrition;

@Repository
public class FoodNutRepositoryImpl implements FoodNutRepository {
	@Autowired
	private SqlSession session;
	private static final String namespace = "mybatis.foodNutMapper.";

	public void loadData() {
		try {
			 DocumentBuilder builder;
			 DocumentBuilderFactory factory;
			 Document document;
			 
			 factory = DocumentBuilderFactory.newInstance();
			 builder = factory.newDocumentBuilder();
			 document = builder.parse(new File("C:\\SSAFY\\work_java_jieun\\SafeFood_Web_Back_End_Seoul_6_LeeJungyeon_KimJieun\\WebContent\\xml\\FoodNutritionInfo.xml"));
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
		return session.selectOne(namespace+"search", name);
	}
	
	
	public int insert(String name, String weight, String cal, String car, String protein, String sugar, String glucose, String salt, String chole, String fat, String transfat) {
		FoodNutrition fn = new FoodNutrition(name, weight, cal, car, protein, sugar, glucose, salt, chole, fat, transfat);
		return session.insert(namespace + "insert", fn);
	}
}
