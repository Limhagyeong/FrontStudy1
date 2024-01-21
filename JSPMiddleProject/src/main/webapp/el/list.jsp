<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.dao.EmpModel"/>
<%
	model.empListData(request); // => controller에 의해 호출될 예정!
	// <% => 자바 코드 완전 제거 ==> html : thymeLeaf통해 for문 돌릴수잇음!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width=800 border=1>
<tr>

<th>사번</th>
<th>이름</th>
<th>부서</th>
<th>입사일</th>
<th>직위</th>
</tr>
<%--
	for(EmpVO vo:list)
	
	=> for(EmpVO vo:request.getAttrebute("list"))
 --%>
<c:forEach var="vo" items="${list }">
<tr>
<td>${vo.empno }</td>
<td>${vo.ename }</td>
<td>${vo.job }</td>
<td>${vo.hiredate }</td>
<td>${vo.sal }</td>
</tr>
</c:forEach>
</table>
</body>
</html>