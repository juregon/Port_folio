package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Qna;

public interface QnaService {
	void add(Qna qna);
	void delete(int num);
	void update(Qna qna);
	List<Qna> searchAll();
	Qna selectOne(int num);
	List<Qna> searchByWriter(String id);
	List<Qna> searchByTitle(String title);
	String check_Id(int num);
}
