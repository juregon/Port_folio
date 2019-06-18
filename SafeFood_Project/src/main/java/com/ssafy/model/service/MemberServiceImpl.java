package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Member;
import com.ssafy.model.repository.MemberRepositoryImpl;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepositoryImpl memberDao;
	
	public void add(Member m) {
		memberDao.add(m);
	}

	public boolean delete(String id) {
		if(memberDao.delete(id) > 0) {
			return true;
		}
		return false;
	}

	public boolean check_pw(String id, String pw) {
		if(memberDao.check_pw(id).equals(pw)) {
			return true;
		}
		return false;
	}

	public Member search(String id) {
		return memberDao.search(id);
	}

	public void update(Member member) {
		Member orgm = memberDao.search(member.getId());
		if(member.getEmail() == "") {
			member.setEmail(orgm.getEmail());
		}
		if(member.getPhone() == "") {
			member.setPhone(orgm.getPhone());
		}
		if(member.getAddress() == "") {
			member.setAddress(orgm.getAddress());
		}
		if(member.getAllergys() == null) {
			member.setAllergys(orgm.getAllergys());
		}
		memberDao.update(member);
	}

	public String check(int code, String id) {
		List<String> allergys = memberDao.check(code, id);
		String msg = "";
		if (allergys.size() > 0) {
			for (int i = 0; i < allergys.size(); i++) {
				msg += allergys.get(i);
				if (i < allergys.size() - 1) {
					msg += ", ";
				}
			}
			msg = "알러지 성분 " + msg + "이(가) 있습니다. 그래도 섭취하시겠습니까?";
		} else {
			msg = "섭취 하시겠습니까 ?";
		}
		return msg;
	}

	public void intake(int code, String id) {
		memberDao.intake(code, id);
	}

	public String passFind(String id, String name) {
		return memberDao.passFind(id, name);
	}

}
