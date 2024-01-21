<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="d.jsp?id=admin&pwd=1234&mode=1">Redirect</a>
	<br>
	<a href="d.jsp?id=admin&pwd=1234&mode=2">Forward</a>
	<%--request 유지를 위해 사이트 주소 변경 하지 않음 => 기존 파일에서 html만 변경해줌 --%>
</body>
</html>