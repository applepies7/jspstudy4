package com.study.free.web;

import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.exception.BizNotFoundException;
import com.study.common.vo.ResultMessageVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;
import com.study.util.CookieBox;

public class FreeViewController implements IController {
	// private IFreeBoardDao freeDao = new FreeBoardDaoOracle();
	private IFreeBoardService freeBoardService = new FreeBoardServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String s = req.getParameter("boNum");
		int num = Integer.parseInt(s);
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
			return "/WEB-INF/views/free/freeView.jsp?bonum=" + s;
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
