package com.ssafy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ssafy.service.FoodService;
import com.ssafy.service.FoodServiceImpl;
import com.ssafy.util.FoodSaxParser;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;
import com.ssafy.vo.User;

public class FoodController {
	FoodService service = new FoodServiceImpl();
	ArrayList<User> userList = new ArrayList<>();
	
	public void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("im index_controller");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/index.jsp");
		req.setAttribute("list", (List)service.foodList());
		
		dispatcher.forward(req, res);
	}
	
	public void login_process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("im login_process_controller");
		System.out.println(userList.size());
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getId());
			System.out.println(userList.get(i).getPass());
		}
		System.out.println(req.getParameter("user_id"));
		System.out.println(req.getParameter("user_password"));
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getId().equals(req.getParameter("user_id")) && 
					userList.get(i).getPass().equals(req.getParameter("user_password"))) {
				HttpSession session = req.getSession(true);
				session.setAttribute("id", userList.get(i).getId());
				System.out.println(userList.get(i).getId() + "님 로그인 성공!");
				break;
			}
		}
		res.sendRedirect("index.mvc");
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("im logout_controller");
		HttpSession session = req.getSession(true);
		session.invalidate();
		res.sendRedirect("index.mvc");
	}
	
	public void product_info(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("im product_info_controller");
		System.out.println(req.getParameter("code"));
		Food temp = service.search(Integer.parseInt(req.getParameter("code")));
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/product_info.jsp");
		req.setAttribute("food", (Food)temp);
		
		dispatcher.forward(req, res);
	}
	
	public void sign_up(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("im sign_up");
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/sign_up.jsp");
		
		dispatcher.forward(req, res);
	}
	
	public void sign_up_process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("user_id");
		String pass = req.getParameter("user_password");
		String name = req.getParameter("user_name");
		String address = req.getParameter("user_address");
		String phone = req.getParameter("user_phone");
		String[] allergy = req.getParameterValues("chkbox");
		
		userList.add(new User(id, pass, name, address, phone, allergy));
		System.out.println("회원가입 완료");
		
		res.sendRedirect("index.mvc");
	}
	
	public void user_info(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		User user = null;
		
		for (int i = 0; i < userList.size(); i++) {
			if(id.equals(userList.get(i).getId())) { // 세션의 유저 id와 같은 id가있으면
				user = userList.get(i);
				break;
			}
		}
		System.out.println("회원정보");
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/user_info.jsp");
		req.setAttribute("user", user);
		
		dispatcher.forward(req, res);
	}
	
	public void user_modify(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("회원수정 호출");
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		User user = null;
		
		for (int i = 0; i < userList.size(); i++) {
			if(id.equals(userList.get(i).getId())) { // 세션의 유저 id와 같은 id가있으면
				user = userList.get(i);
				break;
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/user_modify.jsp");
		req.setAttribute("user", user);
		
		dispatcher.forward(req, res);
	}
	
	public void user_modify_process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String pass = req.getParameter("user_password");
		String name = req.getParameter("user_name");
		String address = req.getParameter("user_address");
		String phone = req.getParameter("user_phone");
		String[] allergy = req.getParameterValues("chkbox");
		System.out.println("id: " + id); 
		for (int i = 0; i < userList.size(); i++) {
			if(id.equals(userList.get(i).getId())) {
				userList.get(i).setPass(pass);
				userList.get(i).setAddress(address);
				userList.get(i).setPhone(phone);
				userList.get(i).setAllergy(allergy);
			}
		}
		System.out.println("회원정보 수정 완료");

		res.sendRedirect("index.mvc");
	}
	
	public void user_withdrawal(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			if(id.equals(userList.get(i).getId())) { // 세션의 유저 id와 같은 id가있으면 >,<
				userList.remove(i);
				break;
			}
		}
		System.out.println("회원탈퇴");
		session.invalidate();
		res.sendRedirect("index.mvc");
	}
	
	public void search_name(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		System.out.println("이름검색");
		String product_name = req.getParameter("product_name");
		System.out.println(product_name);
		List<Food> list = service.searchAll(new FoodPageBean("name", product_name));
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/index.jsp");
		req.setAttribute("list", list);
		
		dispatcher.forward(req, res);
	}
}












