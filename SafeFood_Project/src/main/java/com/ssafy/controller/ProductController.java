package com.ssafy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.ssafy.model.dto.Food;
import com.ssafy.model.dto.FoodNutrition;
import com.ssafy.model.dto.Member;
import com.ssafy.model.service.FoodNutService;
import com.ssafy.model.service.FoodService;
import com.ssafy.model.service.MemberService;
import com.ssafy.model.service.NaverBO;
import com.ssafy.model.service.NoticeService;
import com.ssafy.model.service.QnaService;
import com.ssafy.model.service.ReplyService;

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
	@Autowired
	private NoticeService nService;
	@Autowired
	private ReplyService rService;
	private NaverBO naverBO = new NaverBO();
	private String apiResult = null;
	
	@RequestMapping("main.mvc")
	public String mainPage() {
		return "Main";
	}

	@RequestMapping("list.mvc")
	public String list(Model model) {
		model.addAttribute("list", fService.searchAll());
		return "List";
	}

//	@RequestMapping("listByCompany.mvc")
//	public String listByCompany(Model model) {
//		model.addAttribute("list", fService.searchByCompany());
//		return "List";
//	}
//	
//	@RequestMapping("listByMaterials.mvc")
//	public String listByMaterials(Model model, String materials) {
//		model.addAttribute("list", fService.searchByMaterials(materials));
//		return "List";
//	}
	
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
	public String search(Model model, String search_category, String search) {
		System.out.println("카테고리"+search_category);
		System.out.println("키워드"+search);
		if(search_category.equals("name")) {
			model.addAttribute("list", fService.searchByName(search));
		} else if(search_category.equals("company")) {
			model.addAttribute("list", fService.searchByCompany(search));
		} else if(search_category.equals("material")) {
			model.addAttribute("list", fService.searchByMaterials(search));
		}
		return "List";
	}

	@RequestMapping("join.mvc")
	public String join() {
		return "Join";
	}

	@RequestMapping("joinMember.mvc")
    public String joinMember(Model model, Member member) {
        String msg = mService.check_id(member.getId());
        if (msg == "") {
            mService.add(member);
            return "redirect:main.mvc";
        } else {
            model.addAttribute("status", "fail");
            model.addAttribute("msg", msg);
            return "Main";
        }
    }
    @RequestMapping("login.mvc")
    public String login(Model model, HttpSession session) {
        String naverAuthUrl = naverBO.getAuthorizationUrl(session);
        System.out.println("네이버:" + naverAuthUrl);
        model.addAttribute("url", naverAuthUrl);
        return "Login";
    }
    @RequestMapping("loginMember.mvc")
    public String loginMember(String id, String pass, HttpSession session, Model model) {
        try { // 성공시
        	if(mService.check_id(id) == "") {
        		model.addAttribute("msg", "해당 아이디가 존재하지 않습니다");
        		model.addAttribute("status", "fail");
                return "Login";
        	} else {
	            if (mService.check_pw(id, pass)) { // 해당 멤버가 존재하는 경우
	                session.setAttribute("id", id);
	                return "redirect:main.mvc";
	            } else { // 실패시
	        		model.addAttribute("msg", "비밀번호가 일치하지 않습니다");
	                model.addAttribute("status", "fail");
	                return "Login";
	            }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Login";
    }
    @RequestMapping("loginMember-naver.mvc")
    public String loginMemberNaver(Model model, HttpSession session) {
        String naverAuthUrl = naverBO.getAuthorizationUrl(session);
        System.out.println("네이버:" + naverAuthUrl);
        // 네이버
        model.addAttribute("url", naverAuthUrl);
        /* 생성한 인증 URL을 View로 전달 */
        return "naverlogin";
    }
    @RequestMapping("callback.mvc")
    public String loginMemberCallback(Model model, @RequestParam String code, @RequestParam String state,
            HttpSession session) throws IOException, ParseException {
        OAuth2AccessToken oauthToken;
        oauthToken = naverBO.getAccessToken(session, code, state);
        // 로그인 사용자 정보를 읽어온다.
        apiResult = naverBO.getUserProfile(oauthToken);
        System.out.println(naverBO.getUserProfile(oauthToken).toString());
        model.addAttribute("result", apiResult);
        System.out.println("result" + apiResult);
        /* 네이버 로그인 성공 페이지 View 호출 */
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(apiResult);
        System.out.println("----------------------");
        JSONObject tempObj = (JSONObject) jsonObj.get("response");
        String id = (String) tempObj.get("id");
        String name = (String) tempObj.get("name");
        String email = (String) tempObj.get("email");
        String msg = mService.check_id(id);
        Member member = new Member();
        member.setId(id);
        member.setPw("");
        member.setName(name);
        member.setEmail(email);
        member.setAddress("");
        member.setPhone("");
        if (msg == "") {
            mService.add(member);
        }
        session.setAttribute("id", id);
        return "naversuccess";
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
		if(session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			List<Food> flist = fService.searchIntake(id);
			List<FoodNutrition> fnlist = new ArrayList<>();
			for (int i = 0; i < flist.size(); i++) {
				fnlist.add(fnService.search(flist.get(i).getName()));
			}
			model.addAttribute("flist", flist);
			model.addAttribute("fnlist", fnlist);
			model.addAttribute("fclist", fService.searchIntakeCnt(id));
			return "Intake";
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
	}

	@RequestMapping("totalIntake.mvc")
	public String totalIntake(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		List<Food> flist = fService.searchIntakeTotal(id);
		List<FoodNutrition> fnlist = new ArrayList<>();
		for (int i = 0; i < flist.size(); i++) {
			fnlist.add(fnService.search(flist.get(i).getName()));
		}
		model.addAttribute("flist", flist);
		model.addAttribute("fnlist", fnlist);
		model.addAttribute("fclist", fService.searchIntakeCntTotal(id));
		return "TotalIntake";
	}
	
	@RequestMapping("yesIntake.mvc")
	public String yesIntake(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		List<Food> flist = fService.searchIntakeYes(id);
		List<FoodNutrition> fnlist = new ArrayList<>();
		for (int i = 0; i < flist.size(); i++) {
			fnlist.add(fnService.search(flist.get(i).getName()));
		}
		model.addAttribute("flist", flist);
		model.addAttribute("fnlist", fnlist);
		model.addAttribute("fclist", fService.searchIntakeCntYes(id));
		return "YesIntake";
	}
	
	@RequestMapping("intakeDelete.mvc")
	public String intakeDelete(int code, HttpSession session) {
		String id = (String) session.getAttribute("id");
		if(fService.intakeDelete(id, code) == 0) {
			fService.intakeUpdate(id, code);
		}	
		return "redirect:intakeInfo.mvc";
	}

	@RequestMapping("checkAlg.mvc")
	public String checkAlg(Model model, HttpSession session, int code, int count) {
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			String msg = mService.check(code, id);
			model.addAttribute("msg", msg);
			model.addAttribute("list", fService.searchAll());
			model.addAttribute("code", code);
			System.out.println(count);
			if(count == 0) {
				count = 1;
			}
			model.addAttribute("count", count);
			fService.foodcartDelete(id, code);
			return "List";
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
	}

	@RequestMapping("intake.mvc")
	public String intake(Model model, HttpSession session, int code, int count) {
		String id = (String) session.getAttribute("id");
		mService.intake(code, id, count);
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
	
	
	@RequestMapping("qna-list.mvc")
	public String qnaList() {
		System.out.println("qna_list");
		return "Qna_List";
	}
	
	@RequestMapping("qna-search.mvc")
	public String qnaSearch() {
		System.out.println("qna_search");
		return "Qna_List";
	}

	@RequestMapping("qna-detail.mvc")
	public String qnaDetail(Model model, int num) {
		model.addAttribute("num", num);
		return "Qna_Detail";
	}
	
	@RequestMapping("qna-write.mvc")
	public String qnawrite(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("id", id);
		return "Qna_Write";
	}
	
	@RequestMapping("qna-mod.mvc")
	public String qnaMod(HttpSession session, Model model, int num) {
		model.addAttribute("id", (String) session.getAttribute("id"));
		model.addAttribute("num", num);
		return "Qna_Mod";
	}
	
	@RequestMapping("qna-delete.mvc")
	public String qnaDelete(int num) {
		qService.delete(num);
		return "redirect:qna-list.mvc";
	}
	
	@RequestMapping("notice-list.mvc")
	public String noticeList() {
		System.out.println("notice_List");
		return "Notice_List";
	}
	
	@RequestMapping("notice-detail.mvc")
	public String noticeDetail(Model model, int num) {
		model.addAttribute("num", num);
		return "Notice_Detail";
	}
	
	@RequestMapping("notice-write.mvc")
	public String noticeWrite(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("id", id);
		return "Notice_Write";
	}
	
	@RequestMapping("notice-mod.mvc")
	public String noticeMod(HttpSession session, Model model, int num) {
		model.addAttribute("id", (String) session.getAttribute("id"));
		model.addAttribute("num", num);
		return "Notice_Mod";
	}
	
	@RequestMapping("notice-delete.mvc")
	public String noticeDelete(int num) {
		nService.delete(num);
		return "redirect:notice-list.mvc";
	}
	
	@RequestMapping("foodcart.mvc")
	public String foodCart(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			// 정보 가져오기
			List<Food> flist = fService.searchFoodcart(id);
			List<FoodNutrition> fnlist = new ArrayList<>();
			for (int i = 0; i < flist.size(); i++) {
				fnlist.add(fnService.search(flist.get(i).getName()));
			}
			model.addAttribute("flist", flist);
			model.addAttribute("fnlist", fnlist);
			model.addAttribute("fclist", fService.searchFoodcartCnt(id));
			return "FoodCart";
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
	}
	
	@RequestMapping("insertFoodcart.mvc")
	public String insertFoodcart(HttpSession session, int code, Model model) {
		if(session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			fService.insertFoodcart(id, code);
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
		return "redirect:list.mvc";
	}
	
	@RequestMapping("foodcartDelete.mvc")
	public String deleteFoodcart(HttpSession session, int code) {
		String id = (String) session.getAttribute("id");
		if(fService.foodcartDelete(id, code)==0) {
			fService.foodcartUpdate(id, code);
		}
		return "redirect:foodcart.mvc";
	}
	
	@RequestMapping("checkAlgNew.mvc")
	public String checkAlgNew(Model model, HttpSession session, int code, int count) {
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			String msg = mService.check(code, id);
			model.addAttribute("msg", msg);
			model.addAttribute("list", fService.searchAll());
			model.addAttribute("code", code);
			System.out.println(count);
			if(count == 0) {
				count = 1;
			}
			model.addAttribute("count", count);
			for (int i = 0; i < count; i++) {
				fService.foodcartDelete(id, code);
				fService.foodcartUpdate(id, code);
			}
			return "List";
		} else {
			model.addAttribute("status", "fail");
			model.addAttribute("msg", "로그인 해주세요");
			return "Main";
		}
	}
	
	@RequestMapping("reply-delete.mvc")
	public String replyDelete(int num) {
		System.out.println(num);
		int qNo = rService.getqNo(num);
		rService.delete(num);
		return "redirect:qna-detail.mvc?num="+qNo;
	}
	
}
