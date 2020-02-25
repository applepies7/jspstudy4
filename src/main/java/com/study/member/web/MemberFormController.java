package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.CodeVO;
import com.study.servlet.IController;

public class MemberFormController implements IController {
private ICommonCodeDao codeDao =  new CommonCodeDaoOracle();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<CodeVO> a = codeDao.getCodeListByParent("JB00");
		List<CodeVO> b = codeDao.getCodeListByParent("HB00");

		req.setAttribute("jobs", a);
		req.setAttribute("likes", b);
		System.out.println(a);
		System.out.println(b);

		
		
		
		
		
		
	 	
		
		return "/WEB-INF/views/member/memberForm.jsp";
	}

	
	
}
