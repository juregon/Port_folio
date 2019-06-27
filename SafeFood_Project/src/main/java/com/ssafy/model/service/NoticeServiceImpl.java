package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Notice;
import com.ssafy.model.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeRepository nRepo;
	
	public void add(Notice notice) {
		nRepo.add(notice);
	}

	public int delete(int num) {
		return nRepo.delete(num);
	}

	public void update(Notice notice) {
		nRepo.update(notice);
	}

	public List<Notice> searchAll() {
		return nRepo.searchAll();
	}

	public Notice selectOne(int num) {
		return nRepo.selectOne(num);
	}

	public String check_Id(int num) {
		return nRepo.check_Id(num);
	}

	public Notice selectOneByTitle(String title) {
		return nRepo.selectOneByTitle(title);
	}
}
