package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Reply;

public interface ReplyService {
	void add(Reply reply);
	List<Reply> searchAll(int num);
	void update(Reply reply);
	int delete(int num);
	int getqNo(int num);
}
