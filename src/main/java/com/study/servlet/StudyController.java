package com.study.servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "frontController" 
			,urlPatterns = { "*.wow" }
			,description = "사용자의 요청을 처리하는 메인 컨트롤러"
			
			,initParams = {@WebInitParam(name = "configFile" 
										,value = "/WEB-INF/classes/config/study_uri.properties") }
			,loadOnStartup = 2)
public class StudyController extends HttpServlet {

	private static final long serialVersionUID = 3184356295960934899L;

	private Map<String, IController> controllerMap = new HashMap<String, IController>();
	// 서블릿의 초기화 메서드(init)에서 프로퍼티를 읽어 객체 생성 후 맵에 저장하기
	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		Iterator<?> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = ((String) keyIter.next()).trim();
			String handlerClassName = prop.getProperty(command).trim();
			try {
				// 클래스 로드 -> 인스턴스 생성 -> 맵에 저장
				Class<?> handlerClass = Class.forName(handlerClassName);
				IController handlerInstance = (IController) handlerClass.newInstance();
				 //심플컨트롤러는 클라이언트수만큼  IController객체를 생성하지만 여기는 한개의 객체로 쓰레드로 대응
				 controllerMap.put(command, handlerInstance);
				System.out.printf("%s에 대한 컨틀롤러 %s를 생성하였습니다.\n",command,handlerClassName);
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	} // init

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 처리 전 공통적인 기능이 필요하면 기술한다.
		// 2. 요청을 분석한다.
		req.setCharacterEncoding("Utf-8"); 
		String uri = req.getRequestURI();// study3/free
		uri = uri.substring(req.getContextPath().length());
		System.out.printf("요청 URI = %s\n", uri);
		try {
			String viewPage = null;
			IController controller = null;
			controller = controllerMap.get(uri);
			if (controller != null) {
				// 3. 요청에 따른 기능 수행
				// 4. 기능 수행에 따른 결과를 속성에 저장한다.
				// 각각의 컨트롤(커맨드) 객체는 3,4 의 기능을 위임 받아 수행하고 뷰정보를 리턴한다.
				viewPage = controller.process(req, resp);
				// 5. 알맞은 뷰로 이동 (포워드)
				if (viewPage != null) {
					//1.redirect:https://www.doum.net/......
					//2. redirect:/free/freeList.wow  3.redirect:freeList.wow
					//reditect:3가지 (1:http,https 2.절대경로 3.상대경로
					if (viewPage.startsWith("redirect:")) {
						if (StringUtils.contains(viewPage, "/")) {
							resp.sendRedirect(req.getContextPath() + viewPage.substring("redirect:".length()));
							System.out.println(req.getContextPath() + viewPage.substring("redirect:".length()));
						}else {
						resp.sendRedirect(viewPage.substring("redirect:".length()));
						System.out.println(viewPage.substring("redirect:".length()));}							
					} else {
						// RequestDispatcher 인클루드, 포워드를 전담하는 객체
						RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
						dispatcher.forward(req, resp);
					}
				}
			} else {
				// controller가 널이면 요청에 대한 정보가 없는 것이므로
				System.out.printf("uri=[%s] 에 해당하는 컨트롤러가 존재하지 않습니다.", uri);
				resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			// resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			throw new ServletException(e);
		}
	} // service
}