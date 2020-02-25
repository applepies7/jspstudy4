package com.study.filter;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {
	private List<String> excludeList;
	
	@Override
	public void init(FilterConfig filterConfig)throws ServletException {
		excludeList = new ArrayList<String>();
		excludeList.add("/login/login.wow");
		excludeList.add("/login/loginCheck.wow");
		excludeList.add("/login/logout.wow");
		
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);

		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		
		if (excludeList.contains(uri)) {
			chain.doFilter(request, response);
		}else {
		
		boolean login = false;
		if (session != null) {
			if (session.getAttribute("USER_INFO") != null) {
				login = true;
			}
		}

		System.out.printf("login check filter: url : %s ip: %s \n ", req.getRequestURI(), req.getRemoteAddr());

		if (login) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.wow");
			dispatcher.forward(request, response);
			// 리다이렉트 클라이언트가 이동
			// 포워드 = 서버 내부
		}
	}
	}
	public void destory() {
		excludeList = null;
		
	}
}
