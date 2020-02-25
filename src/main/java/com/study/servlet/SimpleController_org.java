package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.CodeVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;
import com.study.util.CookieBox;

public class SimpleController_org extends HttpServlet {

	// init() : was에서 메모리에 올라갈때 호출됨
	// destory() : was에서 객체가 제거되기전 호출
	// service() : 클라이언트가 요청시 was에 호출
	// sercice = doGet doPost doPut doDelete 등으로 호출
	// freelist.jsp 호출 하면 was는 freelist.jsp와 매핑된 서블릿을 찾아서 호출 freelist_jsp.class

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		String uri = req.getRequestURI();
		out.printf("요청하신 URI는 %s" + uri);

		// 1클라이언트의 요청파악. 2가지 방식
		// 파라미터 controller?cmd=borderList , ? cmd=voardview
		// uri : /board/list /board/view
		// 2. 요청을 처리할 객체(모델)을 호출하여 실행 (서비스 DAO등....)
		// 3. 객체(모델)를 통해 나온결과를 속성에 저장함.
		// 4. 결과로 보여줄 뷰로 포워딩 jsp
		// free/list.wow = /WEB-INF/views/free/freeList.jsp
		// free/view.wow?bonum12 = /WEB-INF/views/free/freeView.jsp
		req.setCharacterEncoding("UTF-8");

		try {
			String view;

			if (uri.contains("/free/freeList.wow")) {
				String s1 = req.getParameter("searchWord");
				String s2 = req.getParameter("searchCategory");
				String s3 = req.getParameter("recordCountPerPage");
				String s4 = req.getParameter("searchType");
				FreeSearchVO searchVO = new FreeSearchVO();
				IFreeBoardDao freeDao = new FreeBoardDaoOracle();
				searchVO.setSearchWord(s1);
				searchVO.setSearchType(s4);
				searchVO.setSearchCategory(s2);
				if (s3 != null) {
					searchVO.setRecordCountPerPage(Integer.parseInt(s3));
				}
				int rowCount = freeDao.getBoardCount(searchVO);
				searchVO.setTotalRecordCount(rowCount);
				searchVO.setting();

				ICommonCodeDao codeDao = new CommonCodeDaoOracle();
				List<CodeVO> catList = codeDao.getCodeListByParent("BC00");
				List<FreeBoardVO> boardList = freeDao.getBoardList(searchVO);

				req.setAttribute("search", searchVO);
				req.setAttribute("boardList", boardList);
				req.setAttribute("catList", catList);
				view = "/WEB-INF/views/free/freeList.jsp";

				// dup_key
				// DUP_SUBMIT_PREVENT

			} else if (uri.contains("/free/freeView.wow")) {
				String s = req.getParameter("boNum");
				int num = Integer.parseInt(s);
				IFreeBoardDao freeDao = new FreeBoardDaoOracle();
				FreeBoardVO viewList = freeDao.getBoard(num);
				if (viewList != null) {
					CookieBox box = new CookieBox(req);
					String readBoard = box.getValue("free");
					if (readBoard == null)
						readBoard = "";
					String pat = "\\b" + num + "\\b";
					if (!Pattern.compile(pat).matcher(readBoard).find()) {
						freeDao.increaseHit(viewList.getBoNum());
						Cookie cookie = CookieBox.createCookie("free", readBoard + num + "|");
						resp.addCookie(cookie);
					}
				}
				req.setAttribute("view", viewList);
				System.out.print(viewList);

				view = "/WEB-INF/views/free/freeView.jsp?bonum=" + s;
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "쉬어가염~");
				return;
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
	}

}
