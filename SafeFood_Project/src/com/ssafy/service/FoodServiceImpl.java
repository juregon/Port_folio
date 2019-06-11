package com.ssafy.service;

import java.util.List;

import com.ssafy.dao.FoodDaoImpl;
import com.ssafy.vo.Food;

public class FoodServiceImpl implements FoodService {
	private FoodDaoImpl dao;
	private String[] allergys;
	
	
	public FoodServiceImpl() {}

	public List<Food> searchAll() {
		return null;
	}

	public Food search(int code) {
		return null;
	}

	public List<Food> searchBest() {
		return null;
	}

	public List<Food> searchBestIndex() {
		return null;
	}

}
