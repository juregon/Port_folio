package com.ssafy.model.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Member_alg;
@Repository
public class MemberRepositoryImpl implements MemberRepository{
	@Autowired
	private SqlSession session;
	
	private static final String namespace = "mybatis.memberMapper.";

	public void add(Member m) {
		session.insert(namespace + "add", m);
		String[] al = m.getAllergys();
		HashMap<String, String> hs = new HashMap<>();
		hs.put("id", m.getId());
		for (int i = 0; al != null && i < al.length; i++) {
			hs.put("alg", al[i]);
			session.insert(namespace + "insert_alg", hs);
		}
	}

	public int delete(String id) {
		return session.delete(namespace + "delete", id);
	}

	public String check_pw(String id) {
		System.out.println("id는 : " + id);
		return session.selectOne(namespace + "check_pw", id);
	}

	public Member search(String id) {
		Member m = new Member();
		Member_alg m_alg = new Member_alg();
		List<Member_alg> list = session.selectList(namespace + "search", id);
		String[] allergys = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if(i == 0) {
				m.setId(list.get(i).getId());
				m.setPw(list.get(i).getPw());
				m.setName(list.get(i).getName());
				m.setAddress(list.get(i).getAddress());
				m.setPhone(list.get(i).getPhone());
				m.setEmail(list.get(i).getEmail());
			}
			if(list.get(i).getAllergy() != null) {
				allergys[i] = list.get(i).getAllergy();
			} else {
				allergys[i] = "알러지 정보 없음";
			}
		}
		m.setAllergys(allergys);
		return m;
	}

	public void update(Member member) {
		String id = member.getId();
		String[] allergys = member.getAllergys();
		session.update(namespace + "update", member);
		session.delete(namespace + "delete_alg", id);
		
		HashMap<String, String> hs = new HashMap<>();
		hs.put("id", id);
		for (int i = 0; allergys != null && i < allergys.length; i++) {
			hs.put("alg", allergys[i]);
			session.insert(namespace + "insert_alg", hs);
		}
	}

	public List<String> check(int code, String id) {
		HashMap<String, Object> hs = new HashMap<>();
		hs.put("code", code);
		hs.put("id", id);
		return session.selectList(namespace + "check", hs);
	}
	
	public void intake(int code, String id) {
		HashMap<String, Object> hs = new HashMap<>();
		hs.put("code", code);
		hs.put("id", id);
		session.insert(namespace + "intake", hs);
	}

	public String passFind(String  id, String name) {
		HashMap<String, String> hs = new HashMap<>();
		hs.put("id", id);
		hs.put("name", name);
		
		return session.selectOne(namespace + "passfind", hs);
	}


}
