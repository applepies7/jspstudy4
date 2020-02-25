package com.study.free.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.ResultMessageVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeDeleteController implements IController {
	private IFreeBoardDao freeDao = new FreeBoardDaoOracle();
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		FreeBoardVO free = new FreeBoardVO();
		BeanUtils.populate(free, req.getParameterMap());


		int res = freeDao.deleteBoard(free);
		req.setAttribute("res", res);

		if (res > 0) {
//			session.removeAttribute("DUP_SUBMIT_PREVENT");
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(true).setTitle("삭제 성공").setMessage("삭제 되었습니다.....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "/WEB-INF/views/common/message.jsp";

		} else {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("삭제 실패").setMessage("삭제 실패 했음....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			req.setAttribute("resultMessage", message);
			return "/WEB-INF/views/common/message.jsp";
		}
	}

}
