package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.FoodNutrition;
import com.ssafy.model.repository.FoodNutRepositoryImpl;

@Service
public class FoodNutServiceImpl implements FoodNutService {
	@Autowired
	private FoodNutRepositoryImpl fnRepo;
	
	//Autowired면 생성자가 필요없나?


	public FoodNutrition search(String name) {
		return fnRepo.search(name);
	}

	public int insert(String name, String weight, String cal, String car, String protein, String sugar, String glucose,
			String salt, String chole, String fat, String transfat) {
		return fnRepo.insert(name, weight, cal, car, protein, sugar, glucose, salt, chole, fat, transfat);
	}

}
