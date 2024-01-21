<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	588p
	param.name => request.getParameter("name");
	<a href="output.jsp">Click</a> => true
	<a href="output.jsp?name=hong">Click</a> => false
	
	${empty param.name } => 값이 없으면 true
						 => List
						 => 검색 결과가 없다 / 장바구니가 비어있다
 --%>
	<h1>${empty param.name }</h1>
</body>
</html>