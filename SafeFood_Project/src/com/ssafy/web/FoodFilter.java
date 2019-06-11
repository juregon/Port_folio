package com.ssafy.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FoodFilter
 */
@WebFilter("*.mvc")
public class FoodFilter implements Filter {
	FoodController controller;

	public void destroy() {};

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		req.setCharacterEncoding("utf-8");
		//클라이언트로부터 들어오는 요청을 구분하는 문자열
		//http://localhost:8080/mvc/main.mvc
		String reqString = req.getServletPath();
		if(reqString.equals("/main.mvc")) {//초기화면 요청
			controller.mainPage(req,res);
		}else if(reqString.equals("/list.mvc")) {
			controller.list(req,res);
		}else if(reqString.equals("/showDetail.mvc")) {//상세정보화면 요청
			controller.detail(req,res);
		}else if(reqString.equals("/search.mvc")) {
			controller.search(req,res);
		} else if (reqString.equals("/join.mvc")) {// 회원가입화면 요청
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Join.jsp");
			dispatcher.forward(req, res);
		} else if (reqString.equals("/joinMember.mvc")) {// 회원정보등록 요청
			controller.join(req, res);
		} else if (reqString.equals("/login.mvc")) {// 로그인화면 요청
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Login.jsp");
			dispatcher.forward(req, res);
		} else if (reqString.equals("/loginMember.mvc")) {// 로그인 요청
			controller.login(req, res);
		} else if (reqString.equals("/logout.mvc")) {
			controller.logout(req, res);
		} else if (reqString.equals("/memberInfo.mvc")) {
			controller.memberInfo(req, res);
		} else if (reqString.equals("/modMember.mvc")) {
			controller.modPage(req, res);
		} else if (reqString.equals("/modMemberInfo.mvc")) {
			controller.modMemberInfo(req, res);
		} else if (reqString.equals("/withdrawl.mvc")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Withdrawl.jsp");
			dispatcher.forward(req, res);
		} else if (reqString.equals("/withdrawlOk.mvc")) {
			controller.delete(req,res);
		} else if (reqString.equals("/intakeInfo.mvc")) {
			controller.intakeInfo(req,res);
		} else if (reqString.equals("/intakeDelete.mvc")) {
			controller.intakeDelete(req,res);
		} else if (reqString.equals("/addFood.mvc")) {
			controller.checkAlg(req,res);
		} else if (reqString.equals("/intake.mvc")) {
			controller.intake(req, res);
		} else if (reqString.equals("/passfind.mvc")) { 
			System.out.println("im passfind");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/Passfind.jsp");
			dispatcher.forward(req, res);
		} else if (reqString.equals("/passfindMember.mvc")) {
			System.out.println("im passfindMember");
			controller.passfind(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		controller = new FoodController();
	}

}
