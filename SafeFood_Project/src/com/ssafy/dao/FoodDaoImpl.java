package com.ssafy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.util.FoodNutritionSAXHandler;
import com.ssafy.util.FoodSAXHandler;
import com.ssafy.util.FoodSaxParser;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;
import com.ssafy.vo.SafeFoodException;

public class FoodDaoImpl implements FoodDao{
	private List<Food> foods;
	public FoodDaoImpl() {
		foods = new ArrayList<Food>();
		loadData();
	}
	/**
	 * 식품 영양학 정보와 식품 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() {
		FoodSaxParser parser = new FoodSaxParser();
		foods = parser.getFoods();//  FoodNutritionSaxPaser를 이용하여 Food 데이터들을 가져온다
	}
	
	public List<Food> foodList(){
		return foods;
	}
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)의 개수를 반환. 
	 * web에서 구현할 내용. 
	 * web에서 페이징 처리시 필요 
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한  식품 개수
	 */
	public int foodCount(FoodPageBean  bean){
		return foods.size();
	}
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<Food> searchAll(FoodPageBean  bean){
		List<Food> finds = new ArrayList<Food>();
		if(bean !=null) {
			String key = bean.getKey();
			String word = bean.getWord();
			if(!key.equals("all") && word!=null && !word.trim().equals("")) { // key null없다, 
				finds = new ArrayList<Food>();
				if(key.equals("name")) {
					for (Food food : foods) {
						if(food.getName().contains(word)) {
							finds.add(food);
						}
					}
				}
				//제조사 검색  구현
				else if(key.equals("maker")) {
					for (Food food : foods) {
						if(food.getMaker().contains(word))
							finds.add(food);
					}
				}
				// 원재료 검색 구현
				else if(key.equals("material")) {
					for (Food food : foods) {
						if(food.getMaterial().contains(word))
							finds.add(food);
					}
				}
				
			}else {
				finds = foods;
			}
		}else {
			finds = foods;
		}
		return finds;
	}
	
	/**
	 * 식품 코드에 해당하는 식품정보를 검색해서 반환. 
	 * @param code	검색할 식품 코드
	 * @return	식품 코드에 해당하는 식품 정보, 없으면 null이 리턴됨
	 */
	public Food search(int code) {

		if (foods.isEmpty())
			return null;
		else {
			for (Food food : foods) {
				if (food.getCode() == code)
					return food;
			}
		}

		// 코드에 맞는 식품 검색하여 리턴
		return null;
	}

	/**
	 * 가장 많이 검색한 Food  정보 리턴하기 
	 * web에서 구현할 내용.  
	 * @return
	 */
	public List<Food> searchBest() {
		return null;
	}
	
	public List<Food> searchBestIndex() {
		return null;
	}
	
	public static void main(String[] args) {
		FoodDaoImpl dao = new FoodDaoImpl();
//		dao.loadData();
		System.out.println("===================================1번 코드 검색=============================");
		System.out.println(dao.search(1));
		System.out.println("===========================material로 검색=================================");
		print(dao.searchAll(new FoodPageBean("material", "감자전분", null, 0)));
		System.out.println("===========================maker로 검색=================================");
		print(dao.searchAll(new FoodPageBean("maker", "빙그레", null, 0)));
		System.out.println("===========================name으로 검색=================================");
		print(dao.searchAll(new FoodPageBean("name", "라면", null, 0)));
		System.out.println("============================================================");
		print(dao.searchAll(null));
		System.out.println("============================================================");
	}
	
	public static void print(List<Food> foods) {
		for (Food food : foods) {
			System.out.println(food);
		}
	}
}
