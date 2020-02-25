package com.study.login.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.vo.ResultMessageVO;
import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class LoginCheckController implements IController {

	private IMemberDao memberDao = new MemberDaoOracle();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		HttpSession session = req.getSession();
		ResultMessageVO messageVO = null;

		String id = req.getParameter("userId");
		String pw = req.getParameter("userPass");
		String remember = req.getParameter("idRemember");
		MemberVO mem = memberDao.getMember(id);

		if (mem != null) {
			if (mem.getMemPass().equals(pw)) {
				// 성공 세션저장
				UserVO vo = new UserVO();
				vo.setUserId(mem.getMemId());
				vo.setUserPass(mem.getMemPass());
				vo.setUserName(mem.getMemName());
				vo.setUserRole("MEMBER");

				System.out.println(vo);
				session.setAttribute("USER_INFO", vo);
//				Cookie cookie = new Cookie("id", id);
//				if (remember != null) {
//					cookie.setPath("/");
//					resp.addCookie(cookie);
//					System.out.println("3단계-쿠키 아이디저장 O");
//					// 쿠키 확인
//					// System.out.println("Service check" + cookie);
//				} else {
//					System.out.println("3단계-쿠키 아이디저장 X");
//					cookie.setMaxAge(0);
//					resp.addCookie(cookie);
//
//				}
//				// remember 조건문
//				// Cookie cookie = new Cookie(,vo.getUserId().);
				return "redirect:/index.jsp";

			} else {
				messageVO = new ResultMessageVO().setResult(false).setTitle("로그인 실패").setMessage("아이디 비번이 틀렸는데?")
						.setUrl("/member/memberForm.wow").setUrlTitle("회원가입");
			}
			req.setAttribute("resultMessage", messageVO);
		}
		return "/WEB-INF/views/common/message.jsp";

	}
}