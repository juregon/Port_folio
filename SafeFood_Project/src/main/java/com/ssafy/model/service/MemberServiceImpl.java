package com.ssafy.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Member;
import com.ssafy.model.repository.MemberRepositoryImpl;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepositoryImpl mRepo;
	
	public void add(Member m) {
		mRepo.add(m);
	}

	public boolean delete(String id) {
		if(mRepo.delete(id) > 0) {
			return true;
		}
		return false;
	}

	public boolean check_pw(String id, String pw) {
		if(mRepo.check_pw(id).equals(pw)) {
			return true;
		}
		return false;
	}

	public String check_id(String id) {
        int a = mRepo.check_id(id);
        String msg = "";
        if(a > 0) {
            msg = "이미 존재하는 아이디 입니다.";
        }
        return msg;
    }
	
	public Member search(String id) {
		return mRepo.search(id);
	}

	public List<Member> searchAll() {
        return mRepo.searchAll();
    }
	
	public void update(Member member) {
		Member orgm = mRepo.search(member.getId());
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
		mRepo.update(member);
	}

	public String check(int code, String id) {
		List<String> allergys = mRepo.check(code, id);
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

	public void intake(int code, String id, int count) {
		mRepo.intake(code, id, count);
	}

	public String passFind(String id, String name) {
		return mRepo.passFind(id, name);
	}

	public List<HashMap> bestIntake(String id) {
		return mRepo.bestIntake(id);
	}
}
