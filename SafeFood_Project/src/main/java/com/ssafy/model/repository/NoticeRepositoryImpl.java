package com.ssafy.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Notice;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
	@Autowired
	private SqlSession session;
	
	private static final String namespace = "mybatis.noticeMapper.";
	
	public void add(Notice notice) {
		session.insert(namespace + "add", notice);
	}

	public int delete(int num) {
		return session.delete(namespace + "delete", num);
	}

	public void update(Notice notice) {
		session.update(namespace + "update", notice);
	}

	public List<Notice> searchAll() {
		return session.selectList(namespace + "searchAll");
	}

	public Notice selectOne(int num) {
		return session.selectOne(namespace + "selectOne", num);
	}

	public String check_Id(int num) {
		return session.selectOne(namespace + "check_Id", num);
	}

	public Notice selectOneByTitle(String title) {
		return session.selectOne(namespace + "selectOneByTitle", title);
	}
}
