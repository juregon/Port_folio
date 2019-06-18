package com.ssafy.model.service;

import com.ssafy.model.dto.FoodNutrition;

public interface FoodNutService {
	FoodNutrition search(String name);
	int insert(String name, String weight, String cal, String car, String protein, String sugar, String glucose, String salt, String chole, String fat, String transfat);
}
