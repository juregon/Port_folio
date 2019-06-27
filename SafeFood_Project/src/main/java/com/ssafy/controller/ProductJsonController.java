package com.ssafy.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.Food;
import com.ssafy.model.dto.Mail;
import com.ssafy.model.dto.Member;
import com.ssafy.model.dto.Notice;
import com.ssafy.model.dto.Qna;
import com.ssafy.model.dto.Reply;
import com.ssafy.model.service.FoodService;
import com.ssafy.model.service.MailService;
import com.ssafy.model.service.MemberService;
import com.ssafy.model.service.NoticeService;
import com.ssafy.model.service.QnaService;
import com.ssafy.model.service.ReplyService;

@RestController
public class ProductJsonController {
	@Autowired
	private QnaService qService;
	@Autowired
	private MemberService mService;
	@Autowired
	private FoodService fService;
	@Autowired
	private NoticeService nService;
	@Autowired
	private ReplyService rService;
	@Autowired
	private MailService maService;

	@RequestMapping("qna-detail.json")
	public Qna qnaDetail(int num) {
		return qService.selectOne(num);
	}

	@RequestMapping("add.json")
	public void add(@RequestBody Qna qna, HttpSession session) {
		qna.setId((String) session.getAttribute("id"));
		qService.add(qna);
	}

	@RequestMapping("mod.json")
	public void update(@RequestBody Qna qna, HttpSession session) {
		qna.setId((String) session.getAttribute("id"));
		qService.update(qna);
	}

	@RequestMapping("qna-list.json")
	public List<Qna> qnaList() {
		System.out.println("qna-list-json");
		return qService.searchAll();
	}

	@RequestMapping("qna-search.json")
	public List<Qna> qnaSearch(String search) {
		System.out.println(search);
		System.out.println("qna-search-json");
		return qService.searchByTitle('%' + search + '%');
	}

	@RequestMapping("best-intake.json")
	public HashMap<String, Object> bestIntake(HttpSession session) {
		List<HashMap> list = mService.bestIntake((String) session.getAttribute("id"));
		HashMap<String, Object> hs = new HashMap<>();
		if (list == null) {
			return hs;
		}
		Food food = fService.search((int) list.get(0).get("code"));
		hs.put("count", list.get(0).get("count"));
		hs.put("name", food.getName());
		hs.put("code", food.getCode());
		System.out.println(hs.toString());
		return hs;
	} // 베스트 섭취 정보 1개 리턴

	@RequestMapping("notice-list.json")
	public List<Notice> noticeList() {
		System.out.println("notice-list-json");
		return nService.searchAll();
	}

	@RequestMapping("notice-detail.json")
	public Notice noticeDetail(int num) {
		return nService.selectOne(num);
	}

	@RequestMapping("notice-add.json")
    public void noticeAdd(@RequestBody Notice notice, HttpSession session) {
        nService.add(notice);
        
        Notice tempNotice = nService.selectOneByTitle(notice.getTitle());
        System.out.println(tempNotice.toString());
        Mail mail = new Mail();
        if(tempNotice.getDivision().equals("공지")) {
            mail.setTitle("공지가 등록되었습니다.");
        } else {
            mail.setTitle("이벤트가 등록되었습니다.");
        }
        mail.setUrl("notice-detail.mvc?num="+tempNotice.getNum());
        mail.setSender("관리자");
        
        if(tempNotice.getDivision().equals("공지")) {
            mail.setCategory("notice");
        } else {
            mail.setCategory("event");
        }
        List<Member> list = mService.searchAll();
        for (int i = 0; i < list.size(); i++) {
            mail.setReceiver(list.get(i).getId());
            if (mail.getReceiver().equals("admin"))
            	continue;
            maService.insert(mail);
        }
    }

	@RequestMapping("notice-mod.json")
	public void noticeUpdate(@RequestBody Notice notice, HttpSession session) {
		nService.update(notice);
	}

	///////////////////////댓글///////////
	@RequestMapping("reply-list.json")
	public List<Reply> replyList(int num) {
		System.out.println("reply-list-json");
		List<Reply> list = rService.searchAll(num);
		System.out.println("--------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		return rService.searchAll(num);
	}

	@RequestMapping("reply-list-check.json")
	public boolean replyListCheck(int num) {
		List<Reply> list = rService.searchAll(num);
		System.out.println("--------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getReplyer());
			if (list.get(i).getReplyer().equals("admin")) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping("reply-add.json")
    public void replyAdd(@RequestBody Reply reply, HttpSession session) {
        Qna qna = qService.selectOne(reply.getqNo());
        System.out.println(reply.toString());
        System.out.println(qna.toString());
        Mail mail = new Mail();
        mail.setTitle("댓글이 등록되었습니다.");
        mail.setUrl("qna-detail.mvc?num="+qna.getNum());
        mail.setSender(reply.getReplyer());
        mail.setReceiver(qna.getId());
        mail.setCategory("reply");
        
        rService.add(reply);
        if (mail.getReceiver().equals(mail.getSender())) {
        } else {
            maService.insert(mail);
        }
    }
	
	@RequestMapping("mail-list.json")
	public List<Mail> mailList(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		return maService.searchAll(id);
	}
	
	@RequestMapping("mail-update.json")
	public void mailUpdate(@RequestBody String num) {
		String[] s = num.split(":");
		s = s[1].split("}");
		maService.mailUpdate(Integer.parseInt(s[0]));
	}
	
	@RequestMapping("mail-isnew.json")
	public int mailIsNew(HttpSession session) {
		String id = (String) session.getAttribute("id");
		return maService.searchCnt(id);
	}
}
