<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">

<!-- header(고정) -->
<jsp:include page="header.jsp"></jsp:include>
<!-- home(정보 변하는 부분) -->
<jsp:include page="home.jsp"></jsp:include>
<!-- footer(고정) -->
<jsp:include page="footer.jsp"></jsp:include>

<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 

<script src="../layout/scripts/jquery.min.js"></script> 
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>