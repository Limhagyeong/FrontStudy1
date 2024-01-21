<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자바(switch case)</h1>
<%
		int i=2;
		switch(i)
		{
		case 0:
%>
		☆☆☆☆☆
<% 
		break;
		case 1:
%>
		★☆☆☆☆
<% 
		break;
		case 2:
%>
		★★☆☆☆
<% 
		break;
		}
%>
<h1>&lt;c:choose&gt;</h1>
<c:set var="i" value="2"/>
<c:choose>
	<c:when test="${i==0 }">☆☆☆☆☆</c:when>
	<c:when test="${i==1 }">★☆☆☆☆</c:when>
	<c:when test="${i==2 }">★★☆☆☆</c:when>
</c:choose>
</body>
</html>