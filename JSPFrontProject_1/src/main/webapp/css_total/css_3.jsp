<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div.wrap{
	width: 900px;
	height: 900px;
	border: 1px solid green;
	position: absolute;
}
img{
	width: 200px;
	height: 200px;
	position: absolute;
}
.a{
	top: 0px;
	left: 0px;
}
.b{
	top: 0px;
	right: 0px;
}
.c{
	bottom: 0px;
	left: 0px;
}
.d{
	bottom: 0px;
	right: 0px;
}
.e{
	top:350px;
	left:350px;
}
</style>
</head>
<body>
 <h1>Absolute 배치</h1>
 <div class="wrap">
 <img src="../img/do.jpg" class="a">
 <img src="../img/do2.jpg" class="b">
 <img src="../img/do3.jpg" class="c">
 <img src="../img/do.jpg" class="d">
 <img src="../img/do2.jpg" class="e">
 </div>
</body>
</html>