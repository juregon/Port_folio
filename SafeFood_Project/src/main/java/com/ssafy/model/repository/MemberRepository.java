package com.ssafy.model.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Member;

@Repository
public interface MemberRepository {
	public void add(Member m);

	public int delete(String id);

	public String check_pw(String id);

	public int check_id(String id);
	
	public Member search(String id);
	
	public List<Member> searchAll();

	public void update(Member member);

	public List<String> check(int code, String id);

	public void intake(int code, String id, int count);

	public String passFind(String id, String name);
	
	public List<HashMap> bestIntake(String id);
}
