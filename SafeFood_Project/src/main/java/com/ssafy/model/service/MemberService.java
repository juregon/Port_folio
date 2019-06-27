package com.ssafy.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Member;

@Service
public interface MemberService {
	public void add(Member m);
	
	public boolean delete(String id);

	public String check_id(String id);
	
	public boolean check_pw(String id, String pass);

	public Member search(String id);

	public List<Member> searchAll();
	
	public void update(Member member);

	public String check(int code, String id);
	
	public void intake(int code, String id, int count);

	public String passFind(String  id, String name);
	
	public List<HashMap> bestIntake(String id);
}
