package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Qna;
import com.ssafy.model.repository.QnaRepository;

@Service
public class QnaServiceImpl implements QnaService{
	@Autowired
	QnaRepository qRepo;

	public void add(Qna qna) {
		qRepo.add(qna);
	}

	public void delete(int num) {
		qRepo.delete(num);
	}

	public void update(Qna qna) {
		qRepo.update(qna);
	}
	
	public List<Qna> searchAll() {
		return qRepo.searchAll();
	}

	public Qna selectOne(int num) {
		return qRepo.selectOne(num);
	}

	public List<Qna> searchByWriter(String id) {
		return qRepo.searchByWriter(id);
	}

	public List<Qna> searchByTitle(String title) {
		return qRepo.searchByTitle(title);
	}

	public String check_Id(int num) {
		return qRepo.check_Id(num);
	}

	
}
