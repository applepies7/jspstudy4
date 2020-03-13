
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="//cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>글 등록</title>
</head>
<body>


	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="container">
		<div class="page-header">
			<h3>글 등록</h3>
		</div>
		<div class="row">
			<form action="freeRegist.wow" method="post">
				<form:form action="freeRegist.wow" commandName=""></form:form>
				<input type="hidden" name="dupKey" value="${dupKey}">
				<table class="table table-striped table-bordered ">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input class="form-control" type="text" name="boTitle"
							value="" required="required"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input class="form-control" type="text" name="boWriter"
							value="" required="required" pattern="[가-힣]+" title="한글만"></td>
					</tr>
					<tr>
					<tr>
						<th>비밀번호</th>
						<td><input class="form-control" type="password" name="boPass"
							value="" required="required" pattern="\w{4,}" title="4자 이상"></td>
					</tr>
					<tr>
						<th>분류</th>
						<td><select class="form-control" name="boCategory"
							required="required">
								<option value="">-- 선택하세요--</option>
								<c:forEach items="${catList}" var="vo">
									<option value="${vo.commCd}">${vo.commNm}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>IP</th>
						<td><%=request.getRemoteAddr()%></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea id="editor" class="form-control" rows="10"
								cols="80" name="boContent"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="pull-left">
								<a href="freeList.wow" class="btn btn-sm btn-default">목록으로</a>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<script>
		CKEDITOR.replace('editor', {

		});
	</script>
</body>
</html>


