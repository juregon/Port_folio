package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.*;

public interface FoodDao {
	void loadData();
	List<Food> searchAll();
	Food search(int code);
	List<Food> search(String name);
	List<Food> searchBest();
	List<Food> searchBestIndex();
	List<Food> searchIntake(String id);
	List<Integer> searchIntakeCnt(String id);
	void intakeDelete(String id, int code);
	
}
