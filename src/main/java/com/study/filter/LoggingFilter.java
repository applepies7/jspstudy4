package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//전처리 부분 (실제 요청에 대한 작업시작 전.)
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		
		long startTime =  System.currentTimeMillis();
		System.out.printf("요청시작:  %s \n",uri);
		//다음으로 자원을 넘김. 전/후 처리 의 기준점.(필터 -> 서블릿)
		chain.doFilter(request, response);
		//후처리 부분 (실제 요청에 대한 작업 이후.)
		System.out.printf("요청끝:  %s \n 소요시간: %d \n",uri , (System.currentTimeMillis()-startTime));
		
	}

}
