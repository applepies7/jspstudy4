<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원가입</title>
  </head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp"%>

<div class="container">
<h3>회원 상세 보기</h3>

<table class="table table-bordered">
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col />
	</colgroup>
	<tr>
		<th>ID</th>
		<td>${mem.memId}</td>
		<th>회원명</th>
		<td>${mem.memName}</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td colspan="3">${mem.memPass}</td>
	</tr>
	<tr>
		<th>주민번호</th>
		<td colspan="3">
			${mem.memRegno1}
			-
			${mem.memRegno2}
		</td>
	</tr>
	<tr>
		<th>생일</th>
		<td colspan="3">${mem.memBir}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td colspan="3">${mem.memZip}</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${mem.memAdd1} <br> 
			${mem.memAdd2}
		</td>
	</tr>
	<tr>
		<th>핸드폰</th>
		<td>${mem.memHp}</td>
		<th>메일</th>
		<td>${mem.memMail}</td>
	</tr>	
	<tr>
		<th>직업</th>
		<td>${mem.memJobnm}</td>
		<th>취미</th>
		<td>${mem.memLikenm}</td>
	</tr>	
	<tr>
		<td colspan="4">
		<div class="pull-left">
		
			<a href="memberList.wow" class="btn btn-sm btn-default">회원 목록</a>
		</div>
		<div class="pull-right">
			<a href="memberEdit.wow?memId=${mem.memId}" class="btn btn-sm btn-default">수정하기
			<%-- <%=request.getParameter("memId")%> ${param.memId} --%>
			</a>
		</div>
		</td>		
	</tr>	
</table>
</div>
</body>
</html>


