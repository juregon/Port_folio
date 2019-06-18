package com.ssafy.model.repository;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.model.dto.Food;

@Repository
public class FoodRepositoryImpl implements FoodRepository {
	@Autowired
	private SqlSession session;

	private static final String namespace = "mybatis.foodMapper.";

	private String[] allergys = { "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레",
			"계란흰자" };

	public void loadData() {
		try {
			DocumentBuilder builder;
			DocumentBuilderFactory factory;
			Document document;

			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(
					"C:\\SSAFY\\work_java_jieun\\SafeFood_Web_Back_End_Seoul_6_LeeJungyeon_KimJieun\\WebContent\\xml\\foodInfo.xml"));
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("food");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node foodNode = nodeList.item(i);
				Element foodElement = (Element) foodNode;

				int code = Integer.parseInt(foodElement.getElementsByTagName("code").item(0).getTextContent());
				String name = foodElement.getElementsByTagName("name").item(0).getTextContent();
				String company = foodElement.getElementsByTagName("maker").item(0).getTextContent();
				String materials = foodElement.getElementsByTagName("material").item(0).getTextContent();

				insert(code, name, company, materials);
				for (int j = 0; j < allergys.length; j++) {
					if (materials.contains(allergys[j])) {
						insertAlg(code, allergys[j]);
					}

				}

			}
			System.out.println("loaded!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertAlg(int code, String allergys) {
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("code", code);
		hs.put("allergys", allergys);
		return session.insert(namespace + "insertAlg", hs);
	}

	public int insert(int code, String name, String company, String materials) {
		Food food = new Food(code, name, company, materials);
		return session.insert(namespace + "insert", food);
	}

//	public List<Food> searchBest() {
//		return null;
//	}

//	@Override
//	public List<Food> searchBestIndex() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public List<Food> searchAll() {
		return session.selectList(namespace + "searchAll");
	}

	public List<Integer> searchIntakeCnt(String id) {
		return session.selectList(namespace + "searchIntakeCnt", id);
	}

	public int intakeDelete(String id, int code) {
		HashMap<String, Object> hs = new HashMap<String, Object>();
		hs.put("id", id);
		hs.put("code", code);
		return session.delete(namespace + "intakeDelete", hs);
	};

	public List<Food> searchIntake(String id) {
		return session.selectList(namespace + "searchIntake", id);
	}

	// 알러지 있는 음식인가?
	public Food search(int code) {
		List<Food> list = session.selectList(namespace + "search", code);
		Food food = new Food();
		String[] allergys = null;
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getAllergy());
		}
		if (list.size() > 0) {
			allergys = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				allergys[i] = list.get(i).getAllergy();
				System.out.println(allergys[i]);
			}
		} 
		food.setCode(list.get(0).getCode());
		food.setCompany(list.get(0).getCompany());
		food.setName(list.get(0).getName());
		food.setMaterials(list.get(0).getMaterials());
		food.setAllergys(allergys);

		return food;
	}

	public List<Food> searchByName(String name) {
		return session.selectList(namespace + "searchByName", name);
	}

}
