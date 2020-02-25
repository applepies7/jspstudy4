package com.study.free.web;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizRegistFailException;
import com.study.common.vo.ResultMessageVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeModifyController implements IController {
private IFreeBoardService freeBoardService =  new FreeBoardServiceImpl();
private ICommonCodeDao codeDao =  new CommonCodeDaoOracle();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {	
			FreeBoardVO free = new FreeBoardVO();
		
		BeanUtils.populate(free, req.getParameterMap());
		freeBoardService.registBoard(free);
		return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");
	
		} catch (BizDuplicateException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("수정 실패")
				.setMessage("등록 실패 했음.... 중복된 글입니다. ").setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		req.setAttribute("resultMessage", message);
		return "/WEB-INF/views/common/message.jsp";
	} catch (BizRegistFailException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("등록 실패")
				.setMessage("글 등록 을 실패 했음....")
				.setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		req.setAttribute("resultMessage", message);
		return "/WEB-INF/views/common/message.jsp";
	}
	}
}

//		
//		int res = freeBoardService.updateBoard(free);
//		req.setAttribute("res", res);
//		if (res > 0) {
//			return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success","utf-8") ;
//		}else {
//				ResultMessageVO message = new ResultMessageVO();
//				message.setResult(false)
//						.setTitle("수정 실패")
//						.setMessage("수정 실패 했음....")
//						.setUrl("/free/freeList.wow")
//						.setUrlTitle("목록으로");
//				req.setAttribute("resultMessage", message);
//				return "/WEB-INF/views/common/message.jsp";
//	
//	
	

//		HttpSession session = req.getSession();
//
//		String pDupKey = req.getParameter("dupKey");
//		String sDupKey = (String) session.getAttribute("DUP_SUBMIT_PREVENT");
//
//		if (pDupKey == null || pDupKey.isEmpty()) {
//			return "redirect:/";
//		} else {
//			if (sDupKey == null || !sDupKey.equals(pDupKey)) {
//				ResultMessageVO message = new ResultMessageVO();
//				message.setResult(false)
//						.setTitle("중복등록")
//						.setMessage("새롭게 작성해...")
//						.setUrl("freeForm.wow")
//						.setUrlTitle("새글 등록");
//				req.setAttribute("resultMessage", message);
//				return "/WEB-INF/views/common/message.jsp";
//			}
//		}
//			session.removeAttribute("DUP_SUBMIT_PREVENT");