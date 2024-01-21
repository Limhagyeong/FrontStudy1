<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 실행 후 사이트 주소 => 마지막에 있는 파일명이 request를 가지고있음
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	String mode=request.getParameter("mode");
	
	if(Integer.parseInt(mode)==1)
	{
		response.sendRedirect("e.jsp");
	}
	else
	{
		pageContext.forward("e.jsp");
		// <jsp:forward page="">
		// MVC구조
	}
%>