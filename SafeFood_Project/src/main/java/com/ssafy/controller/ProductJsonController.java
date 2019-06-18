package com.ssafy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.Qna;
import com.ssafy.model.service.QnaService;

@RestController
public class ProductJsonController {
	@Autowired
	private QnaService qService;
	
	@RequestMapping("qna_detail.json")
	public Qna qnaDetail(int num) {
		return qService.selectOne(num);
	}
	
	@RequestMapping("qna-list.json")
	public List<Qna> searchAll() {
		return qService.searchAll();
	}
}
