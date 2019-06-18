package com.ssafy.model.repository;

import java.util.List;

import com.ssafy.model.dto.Qna;

public interface QnaRepository {
	void add(Qna qna);
	void delete(int num);
	void update(Qna qna);
	List<Qna> searchAll();
	Qna selectOne(int num);
	List<Qna> searchByWriter(String id);
	List<Qna> searchByTitle(String title);
	String check_Id(int num);
}
