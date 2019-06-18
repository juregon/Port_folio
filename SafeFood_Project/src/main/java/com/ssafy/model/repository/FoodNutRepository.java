package com.ssafy.model.repository;

import com.ssafy.model.dto.FoodNutrition;

public interface FoodNutRepository {
	void loadData();
	FoodNutrition search(String name);
	int insert(String name, String weight, String cal, String car, String protein, String sugar, String glucose,
			String salt, String chole, String fat, String transfat);
}
