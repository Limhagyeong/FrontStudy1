<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/>
<jsp:useBean id="bean" class="com.sist.dao.BoardBean">
<jsp:setProperty name="bean" property="*"/>
<%--
	bean.setName(request.getParameter("name"))
	bean.setNo(Interger.paresInt(request.getParameter("no"))
 --%>
</jsp:useBean>
<%
	// id를 객체로 사용할 수 있다 => 컴파일 시 자동으로 자바로 변경 된다
	dao.boardInsert(bean);
	response.sendRedirect("list.jsp");
%>