<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*"%>
<%
	//request.setCharacterEncoding("UTF-8");
	// ==> POST / GET : 톰캣에서 처리해줌
	String fd=request.getParameter("fd");
	String ss=request.getParameter("ss");
	String fno=request.getParameter("fno");
	// 1. 쿠키 생성 
	Cookie cookie=new Cookie("food_"+fno,fno);
	// 2. 저장 경로 설정
	cookie.setPath("/");
	// 3. 저장 기간 설정
	cookie.setMaxAge(60*60*24);
	// 4. 브라우저로 전송
	response.addCookie(cookie);
	
	// 5. 상세보기 이동
	response.sendRedirect("detail.jsp?fno="+fno+"&fd="+fd+"&ss"+URLEncoder.encode(ss,"UTF-8"));
	// sendRedirect는 GET 방식 => 요청값 전송시 반드시 ?
%>