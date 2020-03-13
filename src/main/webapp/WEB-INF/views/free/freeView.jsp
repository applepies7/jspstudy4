
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
				<td>${free.boNum}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${free.boTitle}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${free.boWriter}</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>${free.boCatNm}</td>
			</tr>
			<tr>
				<th>IP</th>
				<td>${free.boIp}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${free.boHit}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="editor" rows="10" cols="80">
                ${free.boContent}</textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${free.boRegDate}</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${free.boModDate}</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="pull-left">

						<a href="freeList.wow" class="btn btn-sm btn-default">글 목록</a>
					</div>
					<div class="pull-right">
						<a href="freeEdit.wow?boNum=${free.boNum}"
							class="btn btn-sm btn-default">수정하기 </a>
					</div>
				</td>
			</tr>
		</table>

		<!-- 		댓글추가 리스트 영역	 -->
		<div class="panel panel-default">
			<div class="panel-body form-horizontal">
				<form name="frm_reply" action="<c:url value='/reply/replyRegist' />"
					method="post" onclick="return false;">
					<input type="hidden" name="reParentNo" value="${free.boNum}">
					<input type="hidden" name="reCategory" value="FREE">
					<div class="form-group">
						<label class="col-sm-2  control-label">댓글</label>
						<div class="col-sm-8">
							<textarea rows="3" name="reContent" class="form-control"></textarea>
						</div>
						<div class="col-sm-2">
							<button id="btn_reply_regist" type="button"
								class="btn btn-sm btn-info">등록</button>
						</div>
					</div>
				</form>
				<div id="id_reply_list_area">

					<div class="row">
						<div class="col-sm-2 text-right">홍길동</div>
						<div class="col-sm-6">
							<pre>내용</pre>
						</div>
						<div class="col-sm-2">12/30 23:45</div>
						<div class="col-sm-2">
							<button name="btn_reply_edit" type="button"
								class=" btn btn-sm btn-info">수정</button>
							<button name="btn_reply_delete" type="button"
								class="btn btn-sm btn-danger">삭제</button>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-2 text-right">홍길동</div>
						<div class="col-sm-6">
							<pre>내용</pre>
						</div>
						<div class="col-sm-2">12/30 23:45</div>
						<div class="col-sm-2">
							<button name="btn_reply_edit" type="button"
								class=" btn btn-sm btn-info">수정</button>
							<button name="btn_reply_delete" type="button"
								class="btn btn-sm btn-danger">삭제</button>
						</div>
					</div>


					<!-- <c:forEach items="${map.data}" var="vo">
					<div class="row">
						<div class="col-sm-2 text-right" ></div>
						<div class="col-sm-6"><pre>내용</pre></div>
						<div class="col-sm-2" >12/30 23:45</div>
						<div class="col-sm-2">
							<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info" >수정</button>
							<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger" >삭제</button>
						</div>
					</div>			
				</c:forEach>
				 -->

				</div>

				<div class="row text-center" id="id_reply_list_more">
					<button id="btn_reply_list_more">
						<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
						더보기
					</button>
				</div>



			</div>
		</div>



	</div>
	<script>
		 CKEDITOR.replace( 'editor',{
			 readOnly : true			 
		 });
		 var currentPageNo = 1;
		 var recordCountPerPage = 10;

		 function fn_reply_list() {
			params = {
				"currentPageNo": currentPageNo,
		 		"recordCountPerPage" : recordCountPerPage,
				"reCategory" : "FREE",
				"reParentNo" : ${free.boNum}
			}
/* 			alert(params)

			$.ajax({
					type:"POST",
					url:"<c:url value='/reply/replyList'/>",
					dataType: "json", 
					data : params,
					success :function(data) {
						console.log("data", data);
						alert(data.msg);
					},
					error:function(req,st,err) {
						alert("ssss")
					}
				}); */
			$.ajax({
				type :"POST",
				url : '<c:url value="/reply/replyList" />',
				dataType : 'json',
				data : params,
				success : function(data) {
					console.log("data", data);
					if(data.result){
						$area = $('#id_reply_list_area');
						$.each(data.data, function(i, row) {
							str = ''
							str += '<div class="row"  data-re-no="'+ row.reNo +'" >'
							str += '	<div class="col-sm-2 text-right" >' + row.reMemName +'</div>'
							str += '	<div class="col-sm-6"><pre>' + row.reContent +'</pre></div>'
							str += '	<div class="col-sm-2" >' + row.reRegDate +'</div>'
							str += '	<div class="col-sm-2">'
							
							if(row.reMemId == '${sessionScope.USER_INFO.userId}'){
							str += '		<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info">수정</button>'
							str += '		<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger" >삭제</button>'
							}
							str += '	</div>'
							str += '</div>'
							$area.append(str);
						}) // $.each				
						
						currentPageNo += 1;
						if(recordCountPerPage > data.count){
							// 더보기 버튼 숨기기 
							$('#btn_reply_list_more').hide();
						}
						
					}else{
						alert(data.msg);
					}			
				},
				error : function(req, st, err) {
					alert(req);
					alert(st);
					alert(err);
				}				
			});
			
		 }

		 $(document).ready(function () {

     			// 수정버튼 클릭
				$('#id_reply_list_area').on('click','button[name=btn_reply_edit]',function(e){
					e.preventDefault();
					// 입력영역 (textarea) 추가 , 기존 내용을 복사 , 기존 내용은 숨기고 
					// (수정)저장버튼(btn_reply_modify)을 보이게하시면 됩니다.			
				}); //  // btn_reply_edit.click

				
				// 삭제버튼 클릭 		
				$('#id_reply_list_area').on('click','button[name=btn_reply_delete]',function(e){
					e.preventDefault();
					$this = $(this);
					res = confirm("글을 삭제하시겠습니까?") // yes = true, no = false
					if(res){
						re = $this.closest('div.row').data('re-no');
						params = {'reNo' : re }
						console.log('params', params);
						$.ajax({
							type :"POST",
							url : '<c:url value="/reply/replyRemove" />',
							dataType : 'json',
							data : params,
							success : function(data) {
								if(data.result){
									$this.closest('div.row').remove();
								}else{
									alert(data.msg);
								}	
							},
							error : function(req, st, err) {
								alert("sssssssssssssssssssssssssssssssss");
							}				
						}); // ajax			
						
					}			
				});  // btn_reply_delete.click 
				
				// 더보기 버튼 클릭 
				$('#btn_reply_list_more').click(function(e) {
					fn_reply_list();
				}); // #btn_reply_list_more.click
				
			//등록버튼 클릭
			$('#btn_reply_regist').click(function() {
				f = document.forms.frm_reply

				params= $(f).serialize();
				alert(params);
				//ajax 데이터 불러오기
				$.ajax({
					type:"POST",
					url:"<c:url value='/reply/replyRegist'/>",
					dataType: "json", 
						data : params,
					success : function(data) {
						console.log("data", data);
						if (data.result) {
							$("#id_reply_list_area").empty();
							document.forms.frm_reply.reContent.value = "";
							currentPageNo = 1;
							fn_reply_list()
						}
					},
					error : function(req, st, err) {
						console.log("req", req);
						console.log(err)
						console.log(st)
						if(req.status == 401){
							alert("로그인이 필요합니다. 로그인페이지로 이동합니다.")
							location.href = "<c:url value='/login/login.wow'/>";
						}else{
							
						console.log(err);
						console.log(st);
						alert("에러 발생하였습니다123.")
						}
					}
				});
			});
			fn_reply_list();
		});
		 
	</script>
</body>
</html>


