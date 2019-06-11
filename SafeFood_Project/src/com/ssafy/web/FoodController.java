package com.ssafy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.dao.FoodDao;
import com.ssafy.dao.FoodDaoImpl;
import com.ssafy.dao.FoodNutDao;
import com.ssafy.dao.MemberDao;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodNutrition;
import com.ssafy.vo.Member;

public class FoodController {
	FoodDao dao = FoodDaoImpl.getInstance();
	FoodNutDao ndao = new FoodNutDao();
	MemberDao mdao = new MemberDao();

	public FoodController() {
//		dao.loadData();
//		ndao.loadData();
	}

	public void mainPage(HttpServletRequest req, HttpServletResponse res) {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Main.jsp");

		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void list(HttpServletRequest req, HttpServletResponse res) {
		List<Food> list = dao.searchAll();
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void detail(HttpServletRequest req, HttpServletResponse res) {
		int code = Integer.parseInt(req.getParameter("code"));
		System.out.println(code);
		Food f = dao.search(code);
		System.out.println(f.getName());
		FoodNutrition fn = ndao.search(f.getName());

		HttpSession session = req.getSession(false);
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			System.out.println(id);
			Member m = mdao.search(id);
			req.setAttribute("m", m);
		} else {
			req.setAttribute("m", null);
		}
		req.setAttribute("f", f);
		req.setAttribute("fn", fn);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Detail.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void search(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("search");
		List<Food> list = dao.search(name);

//		FoodNutrition fn = ndao.search(name);

		HttpSession session = req.getSession(false);
		if (session != null) {
			String id = (String) session.getAttribute("id");
			Member m = mdao.search(id);
			req.setAttribute("m", m);
		} else {
			req.setAttribute("m", null);
		}
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		if(fn==null) {
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Main.jsp");
//			try {
//				dispatcher.forward(req, res);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		req.setAttribute("fn", fn);
//		req.setAttribute("f", f);
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Detail.jsp");
//		try {
//			dispatcher.forward(req, res);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	public void login(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		try {
			if (mdao.check(id, pass)) { // 해당 멤버가 존재하는 경우
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
				res.sendRedirect("main.mvc");
			} else { // 로그인 실패
				req.setAttribute("status", "fail");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Login.jsp");
				dispatcher.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
		try {
			res.sendRedirect("main.mvc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void join(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone1") == "" ? ""
				: req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-" + req.getParameter("phone3");
		String selector = req.getParameter("selector");
		String email = req.getParameter("email1") == "" ? "" : req.getParameter("email1") + "@";
		if (selector.equals("직접입력")) {
			email += req.getParameter("email2");
		} else {
			email += selector;
		}
		String[] al = req.getParameterValues("al");
		mdao.add(new Member(id, pass, name, address, phone, email, al));
		try {
			res.sendRedirect("main.mvc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void memberInfo(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		Member member = mdao.search(id);
		req.setAttribute("member", member);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/MemberInfo.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modPage(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		Member member = mdao.search(id);
		req.setAttribute("member", member);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/modMember.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modMemberInfo(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pass");
		Member orgm = mdao.search(id);
		if (mdao.check(id, pw)) {
			pw = req.getParameter("newPass") == "" ? pw : req.getParameter("newPass");
			String address = req.getParameter("address") == "" ? orgm.getAddress() : req.getParameter("address");
			String phone = orgm.getPhone();
			if (!req.getParameter("phone1").equals("")) {
				phone = req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-"
						+ req.getParameter("phone3");
			}
			String email = orgm.getEmail();
			if (!req.getParameter("email1").equals("")) {
				String selector = req.getParameter("selector");
				email = req.getParameter("email1") + "@";
				if (selector.equals("직접입력")) {
					email += req.getParameter("email2");
				} else {
					email += selector;
				}
			}
			String[] al = req.getParameterValues("al");
			Member member = mdao.update(id, pw, address, phone, email, al);
			req.setAttribute("member", member);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/MemberInfo.jsp");
			try {
				dispatcher.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				req.setAttribute("member", orgm);
				req.setAttribute("status", "fail");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/view/modMember.jsp");
				dispatcher.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		try {
			if (mdao.delete(id)) { // 탈퇴 성공
				session.invalidate();
				req.setAttribute("withdrawl", "success");
				res.sendRedirect("main.mvc");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void intakeInfo(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		String id = "";
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
			Member m = mdao.search(id);
			req.setAttribute("m", m);
		} else {
			req.setAttribute("m", null);
		}
		List<Food> list = dao.searchIntake(id);
		List<Integer> list2 = dao.searchIntakeCnt(id);
		req.setAttribute("list", list);
		req.setAttribute("list2", list2);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/intake.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void intakeDelete(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		String id = "";
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
			Member m = mdao.search(id);
			req.setAttribute("m", m);
		} else {
			req.setAttribute("m", null);
		}
		int code = Integer.parseInt(req.getParameter("code"));
		dao.intakeDelete(id, code);
		intakeInfo(req, res);

	}

	public void checkAlg(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			int code = Integer.valueOf(req.getParameter("code"));
			System.out.println(id + ":" + code);
			// 알러지 성분이 있는 지 체크
			String[] allergys = mdao.check(code, id);
			if (allergys != null) {
//				req.setAttribute("allergy", "fail");
				String msg = "";
				for (int i = 0; i < allergys.length; i++) {
					msg += allergys[i];
					if (i < allergys.length - 1) {
						msg += ", ";
					}
				}
				req.setAttribute("msg", "알러지 성분 " + msg + "이 있습니다. 그래도 섭취하시겠습니까?");
				req.setAttribute("list", dao.searchAll());
				req.setAttribute("code", code);
				try {
					req.getRequestDispatcher("/view/list.jsp").forward(req, res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				intake(req, res);
			}
		} else {
			req.setAttribute("status", "fail");
			req.setAttribute("msg", "로그인 해주세요");
			try {
				req.getRequestDispatcher("/view/Main.jsp").forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void intake(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			int code = Integer.valueOf(req.getParameter("code"));
			System.out.println(id + ":" + code);
			// 알러지 성분이 있는 지 체크
			mdao.intake(code, id);
			req.setAttribute("status", "true");
			req.setAttribute("msg", "내 섭취 정보에 추가되었습니다.");
			try {
				req.getRequestDispatcher("/view/Main.jsp").forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("status", "fail");
			req.setAttribute("msg", "로그인 해주세요");
			try {
				req.getRequestDispatcher("/view/Main.jsp").forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void passfind(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pass = mdao.passFind(id, name);

		req.setAttribute("status", "true");
		if (pass != null) {
			req.setAttribute("msg", "비밀번호는 : " + pass);
		} else {
			req.setAttribute("msg", "아이디나 이름이 일치하지 않습니다.");
		}
		try {
			req.getRequestDispatcher("/view/Main.jsp").forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}