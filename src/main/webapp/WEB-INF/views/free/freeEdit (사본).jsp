<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h3>글 편집</h3>
		</div>
		<div class="row">
			<form action="freeModify.wow" id="id_form" method="post">
					<input type="hidden" name="boNum" value="${view.boNum}">
					<input type="hidden" name="dupKey" value="${dupKey}">
				<table class="table table-striped table-bordered ">
					<colgroup>
						<col width="20%" />
						<col />
					</colgroup>
					<tr>
						<th>글번호</th>
						<td>${view.boNum}</td>
						
					</tr>
					<tr>
						<th>조회수</th><td>${view.boHit}</td>
					
					</tr>
					
					<tr>
						<th>제목</th>
						<td><input class="form-control" type="text" name="boTitle"
							value="${view.boTitle}"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input class="form-control" type="text" name="boWriter"
							value="${view.boWriter}"></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${view.boRegDate}</td>
					</tr>

					<tr>
						<th>비밀번호</th>
						<td><input class="form-control" type="password" name="boPass"
							value="${view.boPass}"></td>
					</tr>
					<tr>
						<th>분류</th>
						<td>
						<select class="form-control" name="boCategory">
								<option value="">-- 선택하세요--</option>
								<c:forEach items="${catList}" var="vo">
									<option value="${vo.commCd}" ${vo.commCd == view.boCategory ? 'selected="selected"' : ""}>${vo.commNm}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<th>IP</th>
						<td><%=request.getRemoteAddr()%></td>

					</tr>
					<tr>
						<th>내용</th>
						<td><textarea class="form-control" id="editor" rows="10" cols="80" name="boContent">${view.boContent }</textarea>
						</td>
					</tr>
					
					<tr>
					
						<td colspan="2">
							<div class="pull-left">
								<a href="freeList.wow" class="btn btn-sm btn-default">목록으로</a>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-sm btn-primary">저장하기</button>
								<button type="submit" id="btn-delete" class="btn btn-sm btn-success" onclick="f_form()" >삭제</button>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<script>
		CKEDITOR.replace('editor');
		function f_form() {
			document.getElementById("id_form").action = "freeDelete.wow";
			document.getElementById("id_form").submit();
		}
		
	</script>
</body>
</html>


