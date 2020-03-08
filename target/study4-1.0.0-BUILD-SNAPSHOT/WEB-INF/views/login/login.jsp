<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/WEB-INF/inc/common_header.jsp" %>
	<title>loginForm </title>

<style type="text/css">

	.login-form {
		width: 385px;
		margin: 30px auto;
	}
    .login-form form {        
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .login-btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .input-group-addon .fa {
        font-size: 18px;
    }
    .login-btn {
        font-size: 15px;
        font-weight: bold;
    }
	.social-btn .btn {
		border: none;
        margin: 10px 3px 0;
        opacity: 1;
	}
    .social-btn .btn:hover {
        opacity: 0.9;
    }
	.social-btn .btn-primary {
        background: #507cc0;
    }
	.social-btn .btn-info {
		background: #64ccf1;
	}
	.social-btn .btn-danger {
		background: #df4930;
	}
    .or-seperator {
        margin-top: 20px;
        text-align: center;
        border-top: 1px solid #ccc;
    }
    .or-seperator i {
        padding: 0 10px;
        background: #f7f7f7;
        position: relative;
        top: -11px;
        z-index: 1;
    }   
</style>
</head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp" %>
<div class="container">
<div class="login-form">
    <form action="<%=request.getContextPath()%>/login/login.wow" method="post">
        <h2 class="text-center">로그인</h2>   
        <div class="form-group">
        	<div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="userId" placeholder="사용자 아이디" required="required">				
            </div>
        </div>
		<div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="userPass" placeholder="비밀번호" required="required">				
            </div>
        </div>        
        <div class="form-group">
            <button type="submit" class="btn btn-primary login-btn btn-block">로그인</button>
        </div>
        <div class="clearfix">
            <label class="pull-left checkbox-inline"><input type="checkbox" name="idRemember"> Remember me</label>
            <a href="#" class="pull-right">비밀번호 찾기</a>
        </div>
		<div class="or-seperator"></div>      
    </form>
    <p class="text-center text-muted small">아직 회원이 아니십니까? <a href="<%=request.getContextPath()%>/member/memberForm.wow">회원가입</a></p>
</div>
</div>
</body>
</html>