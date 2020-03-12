package com.study.free.web;

import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizNotFoundException;
import com.study.common.exception.BizRegistFailException;
import com.study.common.service.ICommonCodeService;
import com.study.common.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;
import com.study.util.CookieBox;

@Controller
@RequestMapping("/free")
public class FreeBoardController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IFreeBoardService freeBoardService;
	@Autowired
	private ICommonCodeService codeService;

	@RequestMapping("/freeList.wow")
	// public String freeList(httpreHttpServletRequest req, FreeSearchVO searchVO)
	// throws Exception {
	public void freeList(ModelMap model, FreeSearchVO searchVO) throws Exception {
		logger.debug("debug searchVO:{}", searchVO);

		List<CodeVO> catList = codeService.getCodeListByParent("BC00");
		List<FreeBoardVO> boardList = freeBoardService.getBoardList(searchVO);
		logger.info("list size = {}", boardList.size());

		model.addAttribute("search", searchVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("catList", catList);

		// return "free/freeList";

	}

	@RequestMapping(value = "/freeView.wow", method = RequestMethod.GET, params = "boNum")
	public String freeView(HttpServletRequest req, HttpServletResponse resp, @RequestParam("boNum") int num)
			throws Exception {

		logger.debug("boNum = {}", num);
		try {
			FreeBoardVO free = freeBoardService.getBoard(num);
			System.out.println(free);
			CookieBox box = new CookieBox(req);
			String readBoard = box.getValue("free");
			if (readBoard == null)
				readBoard = "";
			String pattern = "\\b" + num + "\\b";
			if (!Pattern.compile(pattern).matcher(readBoard).find()) {
				freeBoardService.increaseHit(num);
				Cookie cookie = CookieBox.createCookie("free", readBoard + num + "|");
				resp.addCookie(cookie);
			}
			req.setAttribute("free", free);
			return "free/freeView.jsp?bonum=" + num;
		} catch (BizNotFoundException e) {
			logger.error("조회 오류 boNum={}", num, e);
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("조회실패").setMessage("그런 글 없는데????").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "common/message";

		}
	}

	@RequestMapping(value = "/freeEdit.wow")
	public String freeEdit(int boNum, ModelMap model) throws Exception {
		FreeBoardVO free = freeBoardService.getBoard(boNum);
		List<CodeVO> a = codeService.getCodeListByParent("BC00");
		model.addAttribute("catList", a);
		model.addAttribute("free", free);

		return "free/freeEdit";

	}

	@RequestMapping(value = "/freeModify.wow", method = RequestMethod.POST)
	public String freeModyfy(@ModelAttribute("free") @Valid FreeBoardVO free, BindingResult errors,
			HttpServletRequest req, ModelMap model) throws Exception {

		logger.debug("free = {}", free);
		try {
//			//free의 값을 validation
//			if(StringUtils.isBlank(free.getBoTitle())) {
//				errors.rejectValue("boTitle","","제목은 왜 뺌?");
//			}

			if (errors.hasErrors()) {
				model.addAttribute("catList", codeService.getCodeListByParent("BC00"));
				return "free/freeEdit";

			}
			freeBoardService.modifyBoard(free);

			return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");

		} catch (BizDuplicateException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("수정 실패").setMessage("등록 실패 했음.... 중복된 글입니다. ")
					.setUrl("/free/freeList.wow").setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		} catch (BizRegistFailException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("글 등록 을 실패 했음....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message.jsp";
		}
	}

	@RequestMapping(value = "/freeRegist.wow", method = RequestMethod.POST)
	public String freeRegist(HttpServletRequest req, HttpServletResponse resp, FreeBoardVO free, HttpSession session,
			ModelMap model) throws Exception {
		// @RequestParam("dupKey") String pDupKey
		logger.debug("free = {}", free);
		String pDupKey = req.getParameter("dupKey");
		String sDupKey = (String) session.getAttribute("DUP_SUBMIT_PREVENT");

		if (pDupKey == null || pDupKey.isEmpty()) {
			return "redirect:/";
		} else {
			if (sDupKey == null || !sDupKey.equals(pDupKey)) {
				ResultMessageVO message = new ResultMessageVO();
				message.setResult(false).setTitle("중복등록").setMessage("새롭게 작성해...").setUrl("/free/freeList.wow")
						.setUrlTitle("새글 등록");
				model.addAttribute("resultMessage", message);
				return "common/message";
			}
		}

		free.setBoIp(req.getRemoteAddr());
		try {
			freeBoardService.registBoard(free);
			session.removeAttribute("DUP_SUBMIT_PREVENT");

			return "redirect:/free/freeView.wow?boNum=" + free.getBoNum();
			// return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success",
			// "utf-8");
		} catch (BizDuplicateException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("등록 실패 했음.... 중복된 글입니다. ")
					.setUrl("/free/freeList.wow").setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		} catch (BizRegistFailException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("글 등록 을 실패 했음....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		}

	}

	@RequestMapping(value = "/freeForm.wow")
	public String freeForm(HttpSession session, ModelMap model) throws Exception {

		String dupKey = UUID.randomUUID().toString();
		session.setAttribute("DUP_SUBMIT_PREVENT", dupKey);
		List<CodeVO> a = codeService.getCodeListByParent("BC00");
		model.addAttribute("catList", a);
		model.addAttribute("dupKey", dupKey);

		return "free/freeForm";
	}

	@RequestMapping(value = "/freeDelete.wow", method = RequestMethod.POST)
	public String freeDelete(FreeBoardVO free, HttpServletRequest req, ModelMap model) throws Exception {
		try {
			freeBoardService.removeBoard(free);

			return "redirect:/free/freeList.wow?msg=" + URLDecoder.decode("success", "utf-8");

		} catch (BizDuplicateException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("수정 실패").setMessage("등록 실패 했음.... 중복된 글입니다. ")
					.setUrl("/free/freeList.wow").setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		} catch (BizRegistFailException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("글 등록 을 실패 했음....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message.jsp";
		}
	}

	@RequestMapping(value = "/removeCheckedBoard.wow")
	public String removeCheckedBoard(int[] boNums) {
		logger.debug("boNums = ", boNums);
		freeBoardService.removeCheckedBoard(boNums);

		return "redirect:freeList.wow";
	}

}
