package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.CodeVO;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberListController implements IController {
private	IMemberDao memberDao = new MemberDaoOracle();
private ICommonCodeDao codeDao =  new CommonCodeDaoOracle();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberSearchVO searchVO = new MemberSearchVO();
		BeanUtils.populate(searchVO , req.getParameterMap());
		int rowCount = memberDao.getMemberCount(searchVO);
		//javabean set property와 비슷함. 리퀘스트 파라미터를 맵으로 만들고 \
		//맵을 vo객체에 매칭해줌.
		searchVO.setTotalRecordCount(rowCount);
		searchVO.setting();

		List<CodeVO> a = codeDao.getCodeListByParent("JB00");
		List<MemberVO> list = memberDao.getMemberList(searchVO);
		req.setAttribute("search", searchVO);
		req.setAttribute("list", list);
		req.setAttribute("catList", a);

		System.out.println(searchVO);

		
		return "/WEB-INF/views/member/memberList.jsp";
		
	}

	
	
}
