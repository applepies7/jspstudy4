package com.study.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet.IController;

public class LoginFormController implements IController{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		
		
		return "/WEB-INF/views/login/login.jsp";
	}

}
