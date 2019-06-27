package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Food;

public interface FoodService {
	int insertAlg(int code, String allergys);
	int insert(int code, String name, String company, String materials);
	List<Food> searchAll();
	List<Integer> searchIntakeCnt(String id);
	List<Food> searchIntake(String id);
	List<Integer> searchIntakeCntTotal(String id);
	List<Food> searchIntakeTotal(String id);
	List<Integer> searchIntakeCntYes(String id);
	List<Food> searchIntakeYes(String id);
	int intakeDelete(String id, int code);
	int intakeUpdate(String id, int code);
	Food search(int code);
	List<Food> searchByName(String name);
	List<Food> searchByCompany(String company);
	List<Food> searchByMaterials(String materials);
	List<Food> searchBest();
	List<Food> searchBestIndex();
	int insertFoodcart(String id, int code);
	List<Food> searchFoodcart(String id);
	List<Integer> searchFoodcartCnt(String id);
	int foodcartDelete(String id, int code);
	int foodcartUpdate(String id, int code);
}
