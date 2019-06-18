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

	public int intakeDelete(String id, int code) {
		return fRepo.intakeDelete(id, code);
	}

	public List<Food> searchIntake(String id) {
		return fRepo.searchIntake(id);
	}

	public Food search(int code) {
		return fRepo.search(code);
	}

	public List<Food> searchByName(String name) {
		return fRepo.searchByName(name);
	}

	public List<Food> searchBest() {
		return null;
	}

	public List<Food> searchBestIndex() {
		return null;
	}
}
