<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 
<%
String lang = request.getParameter("lang"); %>

<fmt:setLocale value="<%=lang %>" scope = "session"/>

    <% 
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    %>