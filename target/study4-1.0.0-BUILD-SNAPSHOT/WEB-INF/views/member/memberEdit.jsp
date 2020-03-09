<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<%%>
  	<title>회원수정</title>
  </head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp"%>

<div class="container">
<h3>회원 수정</h3>



<form id ="id-form" action="memberModify.wow" method="post">


<table class="table table-bordered">
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col />
	</colgroup>
	<tr>
		<th>ID</th>
		<td><input type="text" name="memId" value="${mem.memId }" required="required" ></td>
		<th>회원명</th>
		<td><input type="text" name="memName" value="${mem.memName }" required="required" ></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td colspan="3"><input type="text" name="memPass" value="${mem.memPass }" required="required" ></td>
	</tr>
	<tr>
		<th>주민번호</th>
		<td colspan="3">
			<input type="text" name="memRegno1" value="${mem.memRegno1}" placeholder="주민번호 앞 6자리"  >
			-
			<input type="text" name="memRegno2" value="${mem.memRegno2}" placeholder="주민번호 뒤 7자리"  >
		</td>
	</tr>
	<tr>
		<th>생일</th>
		<td colspan="3"><input type="date" name="memBir" value="${mem.memBir}" placeholder="생일입력" required="required" ></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td colspan="3"><input type="text" name="memZip" value="${mem.memZip}" placeholder="우편번호입력"></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="memAdd1" value="${mem.memAdd1}"  placeholder="기본주소 입력"> <br> 
			<input type="text" name="memAdd2" value="${mem.memAdd2}" placeholder="상세주소 입력">
		</td>
	</tr>
	<tr>
		<th>핸드폰</th>
		<td><input type="text" name="memHp" value="${mem.memHp}"  required="required" ></td>
		<th>메일</th>
		<td><input type="email" name="memMail" value="${mem.memMail}"></td>
	</tr>	
	<tr>
		<th id>직업</th>
		<td>
			<select name="memJob">
				<option value="">직업선택</option>
	<c:forEach items="${jobs}" var="jb" varStatus="st">
			<option value="${jb.commCd}" ${jb.commCd == mem.memJob ? 'selected="selected"' : ""}>${jb.commNm}</option>
		</c:forEach>	
	 		</select>

		</td>
		<th>취미</th>
		<td>
			<select name="memLike">
				<option value="" >취미</option>
		<c:forEach items="${likes}" var="hb" >
			<option value="${hb.commCd}" ${hb.commCd == mem.memLike ? 'selected="selected"' : ""}> ${hb.commNm}</option>
		</c:forEach>	
	
			</select>
		</td>
	</tr>	
	<tr>
		<td colspan="4">
			<a href="memberList.wow" class="btn btn-sm btn-default">회원 목록</a>
			<button type="submit"  class="btn btn-sm btn-success" name="action" >변경</button>
			<button type="submit" id="btn-delete" class="btn btn-sm btn-success" name="action" >삭제</button>
		</td>
	</tr>	
</table>
</form>
</div>
<script>
function f_form(){
	    document.getElementById("id_form").action = "memberDelete.wow";
	    document.getElementById("id_form").submit();
}

var fn_frm_submit=function(e) {
	e.preventDefault(); //이벤트 전파방지
	var f = document.forms["id-form"];
	f.action = "memberModify.wow";
	f.submit();
	
}
var fn_frm_delete_click=function(e) {
	e.preventDefault(); //이벤트 전파방지
	var f = document.forms["id-form"];
	f.action = "memberDelete.wow";
	f.submit();
	
}

window.onload = function() {
	document.forms["id-form"].addEventListener("submit",fn_frm_submit);
	document.getElementById("btn-delete").addEventListener("click",fn_frm_delete_click);
		
	}
	

</script>
</body>
</html>


