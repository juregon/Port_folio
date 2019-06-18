package com.ssafy.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Qna;

@Repository
public class QnaRepositoryImpl implements QnaRepository{
	@Autowired
	private SqlSession session;
	
	private static final String namespace = "mybatis.qnaMapper.";

	public void add(Qna qna) {
		session.insert(namespace + "add", qna); 
	}

	public void delete(int num) {
		session.delete(namespace + "delete", num);
	}

	public void update(Qna qna) {
		session.update(namespace + "update", qna);
	}
	
	public List<Qna> searchAll() {
		return session.selectList(namespace + "searchAll");
	}

	public Qna selectOne(int num) {
		return session.selectOne(namespace + "selectOne", num);
	}

	public List<Qna> searchByWriter(String id) {
		return session.selectList(namespace + "searchByWriter", id);
	}

	public List<Qna> searchByTitle(String title) {
		return session.selectList(namespace + "searchByTitle", title);
	}

	public String check_Id(int num) {
		return session.selectOne(namespace + "check_Id", num);
	}
	
	
}
