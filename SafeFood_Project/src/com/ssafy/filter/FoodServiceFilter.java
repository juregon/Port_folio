package com.ssafy.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.ssafy.controller.FoodController;
import com.ssafy.util.FoodSaxParser;
import com.ssafy.vo.Food;

@WebFilter("*.mvc")
public class FoodServiceFilter implements Filter {
	FoodController controller;

	public FoodServiceFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");

		String reqString = req.getServletPath();
		if (reqString.equals("/index.mvc")) {
			System.out.println("im index");
			controller.index(req, res);
		} else if (reqString.equals("/login_process.mvc")) {
			System.out.println("im login");
			controller.login_process(req, res);
		} else if (reqString.equals("/logout.mvc")) {
			System.out.println("im logout");
			controller.logout(req, res);
		} else if (reqString.equals("/sign_up.mvc")) {
			System.out.println("im sign_up");
			controller.sign_up(req, res);
		} else if (reqString.equals("/sign_up_process.mvc")) {
			System.out.println("im sign_up_process");
			controller.sign_up_process(req, res);
		} else if (reqString.equals("/user_info.mvc")) {
			System.out.println("im user_info");
			controller.user_info(req, res);
		} else if (reqString.equals("/user_modify.mvc")) {
			System.out.println("im user_modify");
			controller.user_modify(req, res);
		} else if (reqString.equals("/user_modify_process.mvc")) {
			System.out.println("im user_modify_process");
			controller.user_modify_process(req, res);
		} else if (reqString.equals("/user_withdrawal.mvc")) {
			System.out.println("im user_withdrawal");
			controller.user_withdrawal(req, res);
		} else if (reqString.equals("/product_info.mvc")) {
			System.out.println("im product_info");
			System.out.println(req.getParameter("code"));
			controller.product_info(req, res);
		} else if (reqString.equals("/search_name.mvc")) {
			System.out.println("im search_name");
			controller.search_name(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		controller = new FoodController();
	}

}
