<%@page import="java.net.URLDecoder"%>
<%@page import="com.study.login.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="top-menu">
	<ul>
		<li>홈 </li>
		<li>게시판</li>
		<li>상품소개</li>
		<li> <a href="/study/14/regist.jsp">회원가입</a></li>
		<li> <a href="inc/changeLocale.jsp?lang=en">Eng</a></li>
		<li> <a href="inc/changeLocale.jsp?lang=ko">Korean</a></li>
		<li> <a href="inc/changeLocale.jsp?lang=jp">Japanese</a></li>
		
		
		<%
	UserVO idchk = (UserVO)session.getAttribute("USER_INFO");
	if(idchk != null){
		out.println("<li> <h3>"+ idchk.getUserName() + "님  어서오세요</h3><br>");
		out.println("<li> <a href='11/logout.jsp'>로그아웃</a>");
	}else{
		out.println("<li> <a href='11/login.jsp'>로그인으로 돌아가기</a>");
	}
	
	
%>
		
	</ul>


</div>
