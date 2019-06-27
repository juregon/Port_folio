package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Reply;
import com.ssafy.model.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	ReplyRepository rRepo;
	
	public void add(Reply reply) {
		rRepo.add(reply);
	}

	public List<Reply> searchAll(int num) {
		return rRepo.searchAll(num);
	}

	public void update(Reply reply) {
		rRepo.update(reply);
	}

	public int delete(int num) {
		return rRepo.delete(num);
	}

	public int getqNo(int num) {
		return rRepo.getqNo(num);
	}


}
