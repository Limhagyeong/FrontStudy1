<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.a{
	width: 100px;
	height: 100px;
	position: relative; 
}
.a:nth-child(1){
	
	background-color: red;
	left: 50px;
	z-index: 10
	;
}
.a:nth-child(2){
	background-color: yellow;
	right: -70px;
	top: -20px;
	z-index: 5;
}
.a:nth-child(3){
	background-color: black;
	right: -100px;
	top: -40px;
	z-index: 3;
}
</style>
</head>
<body>
	<div class="a"></div>
	<div class="a"></div>
	<div class="a"></div>
</body>
</html>