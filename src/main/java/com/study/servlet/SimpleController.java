package com.study.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.free.web.FreeDeleteController;
import com.study.free.web.FreeEditController;
import com.study.free.web.FreeFormController;
import com.study.free.web.FreeListController;
import com.study.free.web.FreeModifyController;
import com.study.free.web.FreeRegistController;
import com.study.free.web.FreeViewController;

public class SimpleController extends HttpServlet {

	// init() : was에서 메모리에 올라갈때 호출됨
	// destory() : was에서 객체가 제거되기전 호출
	// service() : 클라이언트가 요청시 was에 호출
	// sercice = doGet doPost doPut doDelete 등으로 호출
	// freelist.jsp 호출 하면 was는 freelist.jsp와 매핑된 서블릿을 찾아서 호출 freelist_jsp.class

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String uri = req.getRequestURI();
		System.out.printf("요청하신 URI는 %s \n", uri);

		// 1클라이언트의 요청파악. 2가지 방식
		// 파라미터 controller?cmd=borderList , ? cmd=voardview
		// uri : /board/list /board/view
		// 2. 요청을 처리할 객체(모델)을 호출하여 실행 (서비스 DAO등....)
		// 3. 객체(모델)를 통해 나온결과를 속성에 저장함.
		// 4. 결과로 보여줄 jsp 뷰로 포워딩 
		// free/list.wow = /WEB-INF/views/free/freeList.jsp
		// free/view.wow?bonum12 = /WEB-INF/views/free/freeView.jsp

		try {
			String view;
			IController controller;

			if (uri.contains("/free/freeList.wow")) {
				controller = new FreeListController();
			
			} else if (uri.contains("/free/freeView.wow")) {
				controller = new FreeViewController();
			} else if (uri.contains("/free/freeForm.wow")) {
				controller = new FreeFormController();
			} else if (uri.contains("/free/freeRegist.wow")) {
				controller = new FreeRegistController();
			} else if (uri.contains("/free/freeEdit.wow")) {
				controller = new FreeEditController();
			} else if (uri.contains("/free/freeModify.wow")) {
				controller = new FreeModifyController();
			} else if (uri.contains("/free/freeDelete.wow")) {
				controller = new FreeDeleteController();
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "쉬어가염~");
				return;
			}
			view = controller.process(req, resp);
			if(view != null){
				if(view.startsWith("redirect:")){
					//
				resp.sendRedirect( req.getContextPath() + view.substring("redirect:".length()) );
				}else{
			//view 포워드
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage(), e);
		}
	}

}
