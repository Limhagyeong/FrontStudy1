<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
	height: 600px;
	border: 1px solid red;
}
.row{
	margin: 0px auto;
	margin-top: 10px;
	width: 800px;
}
/*
	button => 모든 태그에 적용이 가능
	1. class 
		크기 : btn-sm / btn-lg / btn-xs (찜하기, 좋아요, 예약 ..)
		형식 : btn
		색상 : btn-danger / btn-primary / btn-success / info / warning / default
				red				blue		green		cyan	orange		grey
	    적용 : class="btn btn-sm btn-success"
*/
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<input type="button" value="danger"
			class="btn-xs btn-danger">
			<input type="button" value="primary"
			class="btn-xs btn-primary">
			<input type="button" value="success"
			class="btn-xs btn-success">
			<input type="button" value="info"
			class="btn-xs btn-info">
			<input type="button" value="warning"
			class="btn-xs btn-warning">
			<input type="button" value="default"
			class="btn-xs btn-default">
	</div>
	<div style="height: 10px">
	<div class="row">
	<input type="button" value="danger"
			class="btn-sm btn-danger">
			<input type="button" value="primary"
			class="btn-sm btn-primary">
			<input type="button" value="success"
			class="btn-sm btn-success">
			<input type="button" value="info"
			class="btn-sm btn-info">
			<input type="button" value="warning"
			class="btn-sm btn-warning">
			<input type="button" value="default"
			class="btn-sm btn-default">
			
	</div>
	<div class="row">
	<input type="button" value="danger"
			class="btn-lg btn-danger">
			<input type="button" value="primary"
			class="btn-lg btn-primary">
			<input type="button" value="success"
			class="btn-lg btn-success">
			<input type="button" value="info"
			class="btn-lg btn-info">
			<input type="button" value="warning"
			class="btn-lg btn-warning">
			<input type="button" value="default"
			class="btn-lg btn-default">	
	</div>
	<div class="row">
		<a href="#" class="btn btn-sm btn-danger">이전</a>
		<a href="#" class="btn btn-sm btn-info">다음</a>
		<span class="btn btn-sm btn-danger" >수정</span>
		<span  class="btn btn-sm btn-info" >삭제</span>
	</div>
</div>
</div>
</body>
</html>