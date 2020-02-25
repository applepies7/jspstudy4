package com.study.member.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.ResultMessageVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberDeleteController implements IController {
	private IMemberDao memberDao = new MemberDaoOracle();
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());

		int res = memberDao.deleteMember(member);
		req.setAttribute("res", res);

		if (res > 0) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(true)
					.setTitle("삭제 성공")
					.setMessage("삭제 되었습니다....")
					.setUrl("/member/memberList.wow")
					.setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "/WEB-INF/views/common/message.jsp";
		} else {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false)
					.setTitle("삭제 실패")
					.setMessage("삭제 실패 했음....")
					.setUrl("/member/memberList.wow")
					.setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "/WEB-INF/views/common/message.jsp";
		}
	}


}
