<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%--
    	C:\webDev\webStudy\JSPBasicProject_3\src\main\webapp\images
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<%
		String fn=request.getParameter("fn");
	%>
	<img src="../images/<%= fn%>">
	</center>
</body>
</html>