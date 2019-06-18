package com.ssafy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.model.dto.Food;
import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Qna;
import com.ssafy.model.service.FoodNutService;
import com.ssafy.model.service.FoodService;
import com.ssafy.model.service.MemberService;
import com.ssafy.model.service.QnaService;

@Controller // 앞에 @Controller 붙인 클래스는 디스페처가 받은 요청을 처리할 페이지를 결정해주는 클래스
public class ProductController {
	@Autowired
	private MemberService mService;
	@Autowired
	private FoodService fService;
	@Autowired
	private FoodNutService fnService;
	@Autowired
	private QnaService qService;
	
	@RequestMapping("main.mvc")
	public String mainPage() {
		return "Main";
	}

	@RequestMapping("list.mvc")
	public String list(Model model) {
		model.addAttribute("list", fService.searchAll());
		return "List";
	}

	@RequestMapping("showDetail.mvc")
	public String detail(Model model, int code, HttpSession session) {
		Food f = fService.search(code);
		Member m = mService.search((String) session.getAttribute("id"));
		model.addAttribute("f", f);
		model.addAttribute("fn", fnService.search(f.getName()));
		model.addAttribute("m", m);
		return "Detail";
	}

	@RequestMapping("search.mvc")
	public String search(Model model, String search) {
		model.addAttribute("list", fService.searchByName('%' + search + '%'));
		return "List";
	}

	@RequestMapping("join.mvc")
	public String join() {
		return "Join";
	}

	@RequestMapping("joinMember.mvc")
	public String joinMember(Member member) {
		mService.add(member);
		return "redirect:main.mvc";
	}

	@RequestMapping("login.mvc")
	public String login() {
		return "Login";
	}

	@RequestMapping("loginMember.mvc")
	public String loginMember(String id, String pass, HttpSession session, Model model) {
		try { // 성공시
			if (mService.check_pw(id, pass)) { // 해당 멤버가 존재하는 경우
				session.setAttribute("id", id);
				return "redirect:main.mvc";
			} else { // 실패시
				model.addAttribute("status", "fail");
				return "Login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Login";
	}

	@RequestMapping("logout.mvc")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.mvc";
	}

	@RequestMapping("memberInfo.mvc")
	public String memberInfo(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("member", mService.search(id));
		return "MemberInfo";
	}

	@RequestMapping("modMember.mvc")
	public String modPage(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("member", mService.search(id));
		return "modMember";
	}

	@RequestMapping("modMemberInfo.mvc")
	public String modMemberInfo(Member member, String newPw, Model model) {
		String id = member.getId();
		String pw = member.getPw();
		if (mService.check_pw(id, pw)) { // 수정 성공
			if (newPw != "") {
				member.setPw(newPw);
			}
			mService.update(member);
			return "redirect:memberInfo.mvc";
		} else { // 수정 실패
			model.addAttribute("member", mService.search(id));
			model.addAttribute("status", "fail");
			return "modMember";
		}
	}

	@RequestMapping("withdrawl.mvc")
	public String withdrawl() {
		return "Withdrawl";
	}

	@RequestMapping("withdrawlOk.mvc")
	public String delete(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		try {
			if (mService.delete(id)) { // 탈퇴 성공
				session.invalidate();
				model.addAttribute("withdrawl", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:main.mvc";
	}

	@RequestMapping("intakeInfo.mvc")
	public String intakeInfo(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("list", fService.searchIntake(id));
		model.addAttribute("list2", fService.searchIntakeCnt(id));
		return "Intake";
	}

	@RequestMapping("intakeDelete.mvc")
	public String intakeDelete(int code, HttpSession session) {
		String id = (String) session.getAttribute("id");
		fService.intakeDelete(id, code);
		return "redirect:intakeInfo.mvc";
	}

	@RequestMapping("checkAlg.mvc")
	public String checkAlg(Model model, HttpSession session, int code) {
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			String msg = mService.check(code, id);
			model.addAttribute("msg", msg);
			model.addAttribute("list", fService.searchAll());
			model.addAttribute("code", code);
			return "List";
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
	}

	@RequestMapping("intake.mvc")
	public String intake(Model model, HttpSession session, int code) {
		String id = (String) session.getAttribute("id");
		mService.intake(code, id);
		model.addAttribute("status", "true");
		model.addAttribute("msg", "내 섭취 정보에 추가되었습니다.");
		return "Main";
	}

	@RequestMapping("passfind.mvc")
	public String passfind() {
		return "Passfind";
	}

	@RequestMapping("passfindMember.mvc")
	public String passfindMember(String id, String name, Model model) {
		String pw = mService.passFind(id, name);

		model.addAttribute("status", "true");
		if (pw != null) {
			model.addAttribute("msg", "비밀번호는 : " + pw);
		} else {
			model.addAttribute("msg", "아이디나 이름이 일치하지 않습니다.");
		}

		return "Main";
	}
	
	@RequestMapping("qna.mvc")
	public String qnaDetail() {
		return "Qna_List";
	}

}
