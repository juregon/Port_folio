package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Food;
import com.ssafy.model.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	FoodRepository fRepo;
	
	public int insertAlg(int code, String allergys) {
		return fRepo.insertAlg(code, allergys);
	}

	public int insert(int code, String name, String company, String materials) {
		return fRepo.insert(code, name, company, materials);
	}

	public List<Food> searchAll() {
		return fRepo.searchAll();
	}

	public List<Integer> searchIntakeCnt(String id) {
		return fRepo.searchIntakeCnt(id);
	}

	public List<Food> searchIntake(String id) {
		return fRepo.searchIntake(id);
	}
	
	public List<Integer> searchIntakeCntTotal(String id) {
		return fRepo.searchIntakeCntTotal(id);
	}

	public List<Food> searchIntakeTotal(String id) {
		return fRepo.searchIntakeTotal(id);
	}

	public List<Integer> searchIntakeCntYes(String id) {
		return fRepo.searchIntakeCntYes(id);
	}

	public List<Food> searchIntakeYes(String id) {
		return fRepo.searchIntakeYes(id);
	}
	
	public int intakeDelete(String id, int code) {
		return fRepo.intakeDelete(id, code);
	}
	
	public int intakeUpdate(String id, int code) {
		return fRepo.intakeUpdate(id, code);
	}

	public Food search(int code) {
		return fRepo.search(code);
	}

	public List<Food> searchByName(String name) {
		return fRepo.searchByName(name);
	}

	public List<Food> searchByCompany(String company) {
		return fRepo.searchByCompany(company);
	}

	public List<Food> searchByMaterials(String materials) {
		return fRepo.searchByMaterials(materials);
	}
	
	public List<Food> searchBest() {
		return null;
	}

	public List<Food> searchBestIndex() {
		return null;
	}

	public int insertFoodcart(String id, int code) {
		return fRepo.insertFoodcart(id, code);
	}

	public List<Food> searchFoodcart(String id) {
		return fRepo.searchFoodcart(id);
	}

	public List<Integer> searchFoodcartCnt(String id) {
		return fRepo.searchFoodcartCnt(id);
	}

	public int foodcartDelete(String id, int code) {
		return fRepo.foodcartDelete(id, code);
	}

	public int foodcartUpdate(String id, int code) {
		return fRepo.foodcartUpdate(id, code);
	}
}
