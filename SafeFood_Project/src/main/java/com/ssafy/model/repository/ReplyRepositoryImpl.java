package com.ssafy.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Reply;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository{
	@Autowired
	private SqlSession session;
	
	private static final String namespace = "mybatis.replyMapper.";
	
	public void add(Reply reply) {
		session.insert(namespace + "add", reply);
	}

	public List<Reply> searchAll(int num) {
		return session.selectList(namespace + "searchAll", num);
	}

	public void update(Reply reply) {
		session.update(namespace + "update", reply);
	}

	public int delete(int num) {
		return session.delete(namespace + "delete", num); 
	}

	public int getqNo(int num) {
		return session.selectOne(namespace + "selectOne", num);
	}

}
