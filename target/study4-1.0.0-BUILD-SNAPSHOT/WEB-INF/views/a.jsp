<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<meta charset="UTF-8">
<title>title</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="container">
		<h3>${msg}</h3>

		<p>id = ${id}</p>
		\${count} = ${count}<br>

		<c:forEach items="${list}" var="s">
		${s}
</c:forEach>

	</div>
</body>
</html>
