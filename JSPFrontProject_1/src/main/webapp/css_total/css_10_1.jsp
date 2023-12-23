<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	width: 900px;
	border: 1px solid red;
	margin: 0px auto;
}
.top{
	width: 900px;/*100%*/
	height: 100px;
	background-color: cyan;
}
.left{
	width: 150px; 
	height: 500px;
	background-color: yellow;
	float: left;
}
.bottom{
	width: 600px;
	height: 500px;
	background-color: pink;
	float: left;
}
.right{
width: 150px;
	height: 500px;
	background-color: yellow;
	float: right;
}
.center{
    width: 900px;
	height: 100px;
	background-color: orange;
	clear: both;

}

</style>
</head>
<body>
	<div class="container">
		<div class="top"></div>
		<div class="left"></div>
		<div class="bottom"></div>
		<div class="right"></div>
		<div class="center"></div>
	</div>
</body>
</html>