package com.study.login.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.common.vo.ResultMessageVO;
import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;

@Controller
public class LoginController {

	private IMemberDao memberDao = new MemberDaoOracle();
	
	@RequestMapping(value = "/login/login.wow" , method = RequestMethod.GET )
	public String login() throws Exception {
		
		return  "/login/login";
		
		
	}

	@RequestMapping(value = "/login/login.wow" , method = RequestMethod.POST )
	public String loginPost(String userId,String userPass, String idRemember, HttpSession session, ModelMap model,	HttpServletResponse resp) throws Exception {

		
		ResultMessageVO messageVO = null;

		
		MemberVO mem =memberDao.getMember(userId);

		if (mem != null) {
			if (mem.getMemPass().equals(userPass)) {
				// 성공 세션저장
				UserVO vo = new UserVO();
				vo.setUserId(mem.getMemId());
				vo.setUserPass(mem.getMemPass());
				vo.setUserName(mem.getMemName());
				vo.setUserRole("MEMBER");

				session.setAttribute("USER_INFO", vo);

				return "redirect:/index.jsp";

			} else {
				messageVO = new ResultMessageVO().setResult(false).setTitle("로그인 실패").setMessage("아이디 비번이 틀렸는데?")
						.setUrl("/member/memberForm.wow").setUrlTitle("회원가입");
			}
		}else
		model.addAttribute("resultMessage", messageVO);
		return  "common/message";

	}		

	@RequestMapping("/login/logout.wow")
	public String logout(HttpSession session) throws Exception {

		
		session.invalidate();		
		return "redirect:/index.jsp";
	}
	

}
	
	



