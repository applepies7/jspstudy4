package com.study.free.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.exception.BizNotFoundException;
import com.study.common.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeEditController implements IController {
private IFreeBoardService freeBoardService =  new FreeBoardServiceImpl();
private ICommonCodeDao codeDao =  new CommonCodeDaoOracle();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			HttpSession session = req.getSession();
			String dupKey = UUID.randomUUID().toString();
			session.setAttribute("DUP_SUBMIT_PREVENT",dupKey);

			String s = req.getParameter("boNum");
			int num = Integer.parseInt(s);
			FreeBoardVO view = freeBoardService.getBoard(num);
			List<CodeVO> a = codeDao.getCodeListByParent("BC00");
			req.setAttribute("catList", a);
			req.setAttribute("dupKey", dupKey);
			req.setAttribute("view", view);
			System.out.print(view);

			return "/WEB-INF/views/free/freeEdit.jsp";
		} catch (BizNotFoundException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false)
			       .setTitle("조회실패")
			       .setMessage("그런 글 없는데????")
			       .setUrl("/free/freeList.wow")
			       .setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "/WEB-INF/views/common/message.jsp";

			
		}		
		}
	}

	
	
