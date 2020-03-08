<%@page import="com.study.common.dao.CommonCodeDaoOracle"%>
<%@page import="com.study.common.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.study.common.dao.ICommonCodeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>회원가입</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

	<div class="container">
		<h3>회원 가입</h3>
		<form action="memberRegist.wow" method="post">
			<table class="table table-bordered">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col />
				</colgroup>
				<tr>
					<th>ID</th>
					<td><input type="text" name="memId" value=""
						required="required"></td>
					<th>회원명</th>
					<td><input type="text" name="memName" value=""
						required="required"></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td colspan="3"><input type="password" name="memPass" value=""
						required="required"></td>
				</tr>
				<tr>
					<th>주민번호</th>
					<td colspan="3"><input type="text" name="memRegno1" value=""
						placeholder="주민번호 앞 6자리"> - <input type="password"
						name="memRegno2" value="" placeholder="주민번호 뒤 7자리"></td>
				</tr>
				<tr>
					<th>생일</th>
					<td colspan="3"><input type="date" name="memBir" value=""
						placeholder="생일입력" required="required"></td>
				</tr>
				<tr>
					<th>우편번호</th>
					
		
					<td colspan="3"><input type="text" id="sample4_postcode" name="memZip" placeholder="우편번호"><input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="sample4_roadAddress"  name="memAdd1" placeholder="도로명주소">
					<input type="text" id="sample4_jibunAddress"  name="memAdd1" placeholder="지번">
						<span id="guide" style="color:#999;display:none"></span></td>
						
					<th>상세주소</th>
						<td><input type="text"
						name="memAdd2"   id="sample4_detailAddress" value="" placeholder="상세주소 입력"></td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td><input type="text" name="memHp" value=""
						required="required"></td>
					<th>메일</th>
					<td><input type="email" name="memMail" value=""></td>
				</tr>
				<tr>
					<th>직업</th>
					<td><select name="memJob">
							<option value="">직업을 선택하세요</option>
							<c:forEach items="${jobs}" var="jb" varStatus="st">
								<option value="${jb.commCd}">${jb.commNm}</option>
							</c:forEach>
					</select></td>
					<th>취미</th>
					<td><select name="memLike">
							<option value="">취미를 선택하세요</option>
							<c:forEach items="${likes}" var="hb">
								<option value="${hb.commCd}">${hb.commNm}</option>
							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td colspan="4"><a href="memberList.wow"
						class="btn btn-sm btn-default">회원 목록</a>
						<button type="submit" class="btn btn-sm btn-success">가입</button></td>
				</tr>
			</table>
		</form>
	</div>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
</body>
</html>


