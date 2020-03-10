
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>member list</title>
</head>
<style type="text/css">
table.grid th {
	text-align: center;
}
</style>
		
<body>
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="container">
			<div class="page-header">
			<h3>회원 목록</h3>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<form name="frm_search" action="memberList.wow" method="get"
					class="form-horizontal">
					<input type="hidden" name="currentPageNo"
						value="${searchVO.currentPageNo }"> <input type="hidden"
						name="recordCountPerPage" value="${searchVO.recordCountPerPage }">
					<div class="form-group">
						<label for="id_searchType" class="col-sm-2 control-label">검색</label>
						<div class="col-sm-2">
							<select id="id_searchType" name="searchType"
								class="form-control input-sm">
								<option value="">-- 선택 --</option>
								<option value="ID"
									${"ID" eq search.searchType ? 'selected="selected"' : ""}>ID</option>
								<option value="NAME"
									${"NAME" eq search.searchType ? 'selected="selected"' : ""}>이름</option>
								<option value="ADD"
									${"ADD" eq search.searchType ? 'selected="selected"' : ""}>주소</option>
							</select>
						</div>
						<div class="col-sm-2">
							<input type="text" name="searchWord"
								class="form-control input-sm" value="${search.searchWord}"
								placeholder="검색어">
						</div>
						<label for="id_searchCategory"
							class="col-sm-2 col-sm-offset-2 control-label">분류</label>
						<div class="col-sm-2">
							<select id="id_searchCategory" name="searchJob"
								class="form-control input-sm">
								<option value="">-- 선택 --</option>
								<c:forEach items="${jobs}" var="vo">
									<option value="${vo.commCd}"
										${vo.commCd == search.searchJob ? 'selected="selected"' : ""}>${vo.commNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-10 text-right">
							<button type="submit" class="btn btn-sm btn-primary btn-block">
								<i class="fa fa-search"></i>검 색
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div>
			<div class="col-sm-2" style="margin-bottom: 5px">
		전체 ${search.totalRecordCount}건 
				<select id="id_rowsize"
					name="recordCountPerPage" class="form-control input-sm">
					<option value="10"
						${ 10 eq search.recordCountPerPage ? 'selected="selected"' : ""}>10</option>
					<option value="20"
						${ 20 eq search.recordCountPerPage ? 'selected="selected"' : ""}>20</option>
					<option value="30"
						${ 30 eq search.recordCountPerPage ? 'selected="selected"' : ""}>30</option>
					<option value="50"
						${ 50 eq search.recordCountPerPage ? 'selected="selected"' : ""}>50</option>

				</select>

			</div>


			<div class="col-sm-2 col-sm-offset-7 text-right"
				style="margin-bottom: 5px">
				<a href="memberForm.wow" class="btn btn-primary btn-default "> 사용자등록
				</a>
			</div>
		</div>
		<br>
		
			
		<table class="table table-striped table-bordered">
			<colgroup>
				<col width="5%" />
				<col width="10%" />
				<col width="15%" />
				<col />
				<col width="20%" />
				<col width="10%" />
			</colgroup>
			<tr>
				<th>No.</th>
				<th>ID</th>
				<th>회원명</th>
				<th>주소</th>
				<th>직업</th>
				<th>마일리지</th>
			</tr>

			<c:forEach items="${list}" var="vo" varStatus="st">
				<tr>

					<td>${st.count}</td>
					<td><a href="memberView.wow?memId=${vo.memId}">${vo.memId}</a></td>
					<td>${vo.memName}</td>
					<td>${vo.memAdd1}${vo.memAdd2 }</td>
					<td>${vo.memJobnm }</td>
					<td>${vo.memMileage }</td>
				</tr>
			</c:forEach>



		</table>
	
	<nav>
			<p>${search.currentPageNo}</p>

			<ul class="pagination">

				<c:if test="${search.firstPageNoOnPageList > search.pageSize }">
					<li class='page-item '><a  id="priv10" class="page-link"
						href="#" data-page="${search.currentPageNo - 10 }" ><span
							class="page-link"><10</span></a></li>
				</c:if>
				<c:if test="${search.currentPageNo >1  }">
					<li class='page-item '><a id="priv" class="page-link"
						href="#" data-page="${search.currentPageNo - 1  }"><span
							class="page-link"><</span></a></li>
				</c:if>


				<c:forEach var="i" begin="${search.firstPageNoOnPageList }"
					end="${search.lastPageNoOnPageList }">
					<c:if test="${search.currentPageNo eq i }">

						<li class="page-item active"><a class="page-link" href="#"><span
								class="page-link">${i }<span class="sr-only">(current)</span></span></a>
					</c:if>
					<c:if test="${search.currentPageNo ne i }">


						<li class="page-item"><a class="page-link" href="#"
							<%-- onclick="fn_goPage(${i})" --%> data-page="${i }" > ${i }</a>
					</c:if>
				</c:forEach>

				<c:if test="${search.currentPageNo < search.totalPageCount }">
					<li class='page-item'><a id="next"class="page-link" href="#"
						<%--onclick= "fn_goPage(${search.currentPageNo +1})" --%> data-page="${search.currentPageNo +1}" >></a></li>
				</c:if>
				<c:if test="${search.lastPageNoOnPageList < search.totalPageCount }">
					<li class='page-item'><a id="next10" class="page-link" href="#"
						<%--onclick= "fn_goPage(${search.totalPageCount-search.currentPageNo >= search.pageSize ? search.currentPageNo + 10 : search.totalPageCount  } )"--%> data-page = "${search.totalPageCount-search.currentPageNo >= search.pageSize ? search.currentPageNo + 10 : search.totalPageCount  }">
							10></a></li>
				</c:if>
			</ul>

		</nav>

	</div>
	<script type="text/javascript">

function fn_goPage() {
 var f = document.forms["frm_search"];
 console.log("page" , this.dataset.page)
 f.currentPageNo.value = this.dataset.page;
 f.submit();
}

function fn_frm_search_submit() {
	var f = document.forms["frm_search"]
	 f.currentPageNo.value = 1;
	 f.submit();
	 	
}

function fn_recordCountPerPage_Change() {
	var f = document.forms["frm_search"]
	 f.currentPageNo.value = 1;
	f.recordCountPerPage.value = this.value
	console.log("rowsize change:", "curpage" ,f.currentPageNo.value, "setpagesize",f.recordCountPerPage.value)
	 f.submit();
}

window.onload = function() {
	document.forms["frm_search"].addEventListener("submit",fn_frm_search_submit);
	document.getElementById("id_rowsize").addEventListener("change", fn_recordCountPerPage_Change);
	document.querySelectorAll(".pagination li a").forEach(obj => { obj.addEventListener("click", fn_goPage);});

}


</script>
	
</body>
</html>





