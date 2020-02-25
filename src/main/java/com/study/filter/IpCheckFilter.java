package com.study.filter;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.vo.ResultMessageVO;

public class IpCheckFilter implements Filter {
	private Map<String, String> accessMap = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// "A"ccess , "D"enied
		accessMap = new HashMap<String, String>();
		accessMap.put("192.168.20.46", "A");
		accessMap.put("0:0:0:0:0:0:0:1", "A");
		accessMap.put("127.0.0.1", "A");
		accessMap.put("192.168.20.37", "A");
		accessMap.put("192.168.20.36", "A");
		accessMap.put("192.168.20.32", "A");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResultMessageVO messageVO = null;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String key = req.getRemoteAddr();
		String value = accessMap.get(key);

		if (value != null) {
			if (value.equals("A")) {
				chain.doFilter(request, response);
			} else {

				messageVO = new ResultMessageVO().setResult(false).setTitle("접근거부").setMessage("너 차단인데??");
				req.setAttribute("resultMessage", messageVO);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/message.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			resp.sendRedirect(req.getContextPath()+"/");
		}

	}

	@Override
	public void destroy() {
		accessMap = null;

	}

}
