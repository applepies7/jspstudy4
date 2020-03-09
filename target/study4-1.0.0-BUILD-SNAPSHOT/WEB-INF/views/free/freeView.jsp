
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="//cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>회원가입</title>
</head>
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>

	<div class="container">
		<h3>글 보기</h3>

		<table class="table table-bordered">
			<colgroup>
				<col width="10%" />
				<col width="30%" />

			</colgroup>
			<tr>
				<th>글번호</th>
				<td>${view.boNum}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${view.boTitle}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${view.boWriter}</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>${view.boCatNm}</td>
			</tr>
			<tr>
				<th>IP</th>
				<td>${view.boIp}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${view.boHit}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="editor" rows="10" cols="80">
                ${view.boContent}</textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${view.boRegDate}</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${view.boModDate}</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="pull-left">

						<a href="freeList.wow" class="btn btn-sm btn-default">글 목록</a>
					</div>
					<div class="pull-right">
						<a href="freeEdit.wow?boNum=${view.boNum}"
							class="btn btn-sm btn-default">수정하기 </a>
					</div>
				</td>
			</tr>
		</table>
	</div>
		<script>
		 CKEDITOR.replace( 'editor',{
			 readOnly : true			 
		 } );
	</script>
</body>
</html>


