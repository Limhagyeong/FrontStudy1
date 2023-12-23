<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	비교 연산자 
	=== (==) 같다
	!== (!=) 같지 않다
	===================> 숫자만 아니라 문자열도 비교시에 처리 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a=10
	let b=5
	console.log("a===b"+(a===b))
	console.log("a!==b"+(a!==b))
	console.log("a<b"+(a<b))
	console.log("a>b"+(a>b))
	console.log("a<=b"+(a<=b))
	console.log("a>=b"+(a>=b))
	
	let c="Hello"
	let d="JavaScript"
	console.log("c===d"+(c===d))
	console.log("c!==d"+(c!==d))
	console.log("c<d"+(c<d))
	console.log("c>d"+(c>d))
	console.log("c<=d"+(c<=d))
	console.log("c>=d"+(c>=d))
	
}
</script>
</head>
<body>

</body>
</html>