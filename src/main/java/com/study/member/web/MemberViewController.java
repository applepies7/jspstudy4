package com.study.member.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberViewController implements IController {
	private IMemberDao memberDao = new MemberDaoOracle();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("memId");
		
		MemberVO mem = memberDao.getMember(id);
		req.setAttribute("mem", mem);
		

		return "/WEB-INF/views/member/memberView.jsp?memId=" + id;
	}

}
