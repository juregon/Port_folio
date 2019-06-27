package com.ssafy.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Mail;

@Repository
public class MailRepositoryImpl implements MailRepository{
	@Autowired
	private SqlSession session;
	private static final String namespace = "mybatis.mailMapper.";
	
	
	public int insert(Mail mail) {
        return session.insert(namespace+"insert", mail);
    }

	public List<Mail> searchAll(String id) {
		return session.selectList(namespace+"searchAll", id);
	}

	public int mailUpdate(int num) {
		return session.update(namespace+"update", num);
	}

	public int searchCnt(String id) {
		return session.selectOne(namespace+"searchCnt", id);
	}

}
