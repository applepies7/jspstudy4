package com.study.free.web;

import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizNotFoundException;
import com.study.common.exception.BizRegistFailException;
import com.study.common.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;
import com.study.util.CookieBox;



@Controller
public class FreeBoardController {

	private IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle();
	

	@RequestMapping("/free/freeList.wow")
	//public String freeList(httpreHttpServletRequest req, FreeSearchVO searchVO) throws Exception {
	public void freeList(ModelMap model, FreeSearchVO searchVO) throws Exception {

		List<CodeVO> catList = codeDao.getCodeListByParent("BC00");
		List<FreeBoardVO> boardList = freeBoardService.getBoardList(searchVO);

		model.addAttribute("search", searchVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("catList", catList);

		//return "free/freeList";

	}
	
	@RequestMapping(value = "/free/freeView.wow" , method = RequestMethod.GET, params ="boNum" )
	public String freeView(HttpServletRequest req, HttpServletResponse resp, @RequestParam("boNum") int num) throws Exception {
		
		try {
			FreeBoardVO view = freeBoardService.getBoard(num);
			System.out.println(view);
			CookieBox box = new CookieBox(req);
			String readBoard = box.getValue("free");
			if (readBoard == null) 
				readBoard = "";	
			String pattern = "\\b" + num + "\\b";
			if (!Pattern.compile(pattern).matcher(readBoard).find()) {
				freeBoardService.increaseHit(view.getBoNum());
				Cookie cookie = CookieBox.createCookie("free", readBoard + num + "|");
				resp.addCookie(cookie);
			}
			req.setAttribute("view", view);
			return "free/freeView.jsp?bonum=" + num;
		} catch (BizNotFoundException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false)
			       .setTitle("조회실패")
			       .setMessage("그런 글 없는데????")
			       .setUrl("/free/freeList.wow")
			       .setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "common/message";

			
		}
	}
	@RequestMapping(value = "/free/freeEdit.wow")
	public String freeEdit(int boNum, ModelMap model) throws Exception{
		FreeBoardVO view = freeBoardService.getBoard(boNum);
		List<CodeVO> a = codeDao.getCodeListByParent("BC00");
		model.addAttribute("catList", a);
		model.addAttribute("view", view);

		
		return "free/freeEdit";
		
	}
	
	@RequestMapping(value = "/free/freeModify.wow", method = RequestMethod.POST)
	public String freeModyfy(FreeBoardVO free , HttpServletRequest req, ModelMap model) throws Exception {
		try {
		freeBoardService.modifyBoard(free);
	
		return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");
	
		} catch (BizDuplicateException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("수정 실패")
				.setMessage("등록 실패 했음.... 중복된 글입니다. ").setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		model.addAttribute("resultMessage", message);
		return "common/message";
	} catch (BizRegistFailException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("등록 실패")
				.setMessage("글 등록 을 실패 했음....")
				.setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		model.addAttribute("resultMessage", message);
		return "common/message.jsp";
	} 
	}
	@RequestMapping(value = "/free/freeRegist.wow", method = RequestMethod.POST)
	public String registFree(HttpServletRequest req, HttpServletResponse resp ,FreeBoardVO free, HttpSession session , ModelMap model) throws Exception {
		//@RequestParam("dupKey") String pDupKey 

		
		String pDupKey = req.getParameter("dupKey");
		String sDupKey = (String) session.getAttribute("DUP_SUBMIT_PREVENT");

		if (pDupKey == null || pDupKey.isEmpty()) {
			return "redirect:/";
		} else {
			if (sDupKey == null || !sDupKey.equals(pDupKey)) {
				ResultMessageVO message = new ResultMessageVO();
				message.setResult(false).setTitle("중복등록").setMessage("새롭게 작성해...").setUrl("/free/freeList.wow")
						.setUrlTitle("새글 등록");
				req.setAttribute("resultMessage", message);
				return "common/message";
			}
		}

		free.setBoIp(req.getRemoteAddr());
		try {
			freeBoardService.registBoard(free);
			session.removeAttribute("DUP_SUBMIT_PREVENT");
			return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");
		} catch (BizDuplicateException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false)
					.setTitle("등록 실패")
					.setMessage("등록 실패 했음.... 중복된 글입니다. ")
					.setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		} catch (BizRegistFailException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false)
					.setTitle("등록 실패")
					.setMessage("글 등록 을 실패 했음....")
					.setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		}

	}
	@RequestMapping(value = "/free/freeForm.wow")
	public String freeForm (HttpServletRequest req, HttpServletResponse resp, HttpSession session, ModelMap model) throws Exception {
		
		String dupKey = UUID.randomUUID().toString();
		session.setAttribute("DUP_SUBMIT_PREVENT",dupKey);
		List<CodeVO> a = codeDao.getCodeListByParent("BC00");
		model.addAttribute("catList", a);
		req.setAttribute("dupKey", dupKey);
	
		return "free/freeForm";
	}
	
	@RequestMapping(value = "/free/freeDelete.wow", method = RequestMethod.POST)
	public String freeDelete(FreeBoardVO free , HttpServletRequest req, ModelMap model) throws Exception {
		try {
		freeBoardService.removeBoard(free);
	
		return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");
	
		} catch (BizDuplicateException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("수정 실패")
				.setMessage("등록 실패 했음.... 중복된 글입니다. ").setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		model.addAttribute("resultMessage", message);
		return "common/message";
	} catch (BizRegistFailException e) {
		ResultMessageVO message = new ResultMessageVO();
		message.setResult(false)
				.setTitle("등록 실패")
				.setMessage("글 등록 을 실패 했음....")
				.setUrl("/free/freeList.wow")
				.setUrlTitle("목록으로");
		model.addAttribute("resultMessage", message);
		return "common/message.jsp";
	} 
	}
	
	
}
	
