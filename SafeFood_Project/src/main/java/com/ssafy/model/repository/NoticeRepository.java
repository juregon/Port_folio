package com.ssafy.model.repository;

import java.util.List;

import com.ssafy.model.dto.Notice;

public interface NoticeRepository {
	void add(Notice notice);
	int delete(int num);
	void update(Notice notice);
	List<Notice> searchAll();
	Notice selectOne(int num);
	String check_Id(int num);
	Notice selectOneByTitle(String title);
}
