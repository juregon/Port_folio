package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Food;

public interface FoodService {
	int insertAlg(int code, String allergys);
	int insert(int code, String name, String company, String materials);
	List<Food> searchAll();
	List<Integer> searchIntakeCnt(String id);
	int intakeDelete(String id, int code);
	List<Food> searchIntake(String id);
	Food search(int code);
	List<Food> searchByName(String name);
	List<Food> searchBest();
	List<Food> searchBestIndex();
}
