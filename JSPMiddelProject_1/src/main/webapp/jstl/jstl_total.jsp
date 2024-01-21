<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--
 		EL => 출력
 			=> ${request.getAttribute("name")} => 변수명 X => 키 명 O
 					${name}
 				 ======================
 			   ${session.getAttribute()}
 		JSTL => 제어문 / URL / 변환 / String 메소드 호출 
 		==== core / fmt / fn 
 		==== JSP는 데이터베이스 연동하는 곳이 아니라 => 전송받은 데이터만 출력하는 파일로 사용 (View)
 			 보안문제 , 자바와 혼합 (퍼블리셔)
 			 <c:forEach>
 			 <c:forTokens>
 			 <c:if>
 			 <c:choose>
 			 <c:set>
 			 <c:out>
 			 
 			 <fmt:formatDate>
 			 <fmt:fotmatNumber>
 			 
 			 ${fn:String메소드} => 자바에서 제어 => 사용빈도수 낮음
  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>