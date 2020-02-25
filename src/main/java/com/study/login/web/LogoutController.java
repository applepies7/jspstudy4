package com.study.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.servlet.IController;

public class LogoutController implements IController{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		
		session.invalidate();		
		return "redirect:/index.jsp";
	}

}
