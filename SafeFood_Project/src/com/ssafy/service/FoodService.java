package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Food;

public interface FoodService {
	public List<Food> searchAll();
	public Food search(int code);
	public List<Food> searchBest();
	public List<Food> searchBestIndex();
}
