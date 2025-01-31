<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
	margin: 0px auto;
	width: 800px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">자유게시판</h2>
			<div class="row row1" style="height: 500px;">
				<table class="table">
					<tr>
						<td><a href="../board/insert.do"
							class="btn btn-sm btn-danger">새글</a></td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<th class="text-center" width=10%>번호</th>
						<th class="text-center" width=50%>제목</th>
						<th class="text-center" width=10%>이름</th>
						<th class="text-center" width=20%>작성일</th>
						<th class="text-center" width=10%>조회수</th>
					</tr>
					<c:set var="count" value="${count }" />
					<c:forEach var="vo" items="${list }">
						<tr>
							<td class="text-center" width=10%>${count }</td>
							<td width=50%><a href="../board/detail.do?no=${vo.no}">${vo.subject }</a></td>
							&nbsp;<c:if test="${today == vo.dbday }">
							<sup><img src="../board/new.gif"></sup>
							</c:if>
							<td class="text-center" width=10%>${vo.name }</td>
							<td class="text-center" width=20%>${vo.dbday }</td>
							<td class="text-center" width=10%>${vo.hit }</td>
						</tr>
						<c:set var="count" value="${count-1 }"/>
					</c:forEach>
					<tr>
					<td colspan="5" class="text-center">
					<a href="#" class="btn btn-danger btn-sm">이전</a>
					${curpage } page / ${totalpage } pages
					<a href="#" class="btn btn-danger btn-sm">다음</a>
					</td>
					</tr>
				</table>
			</div>
		</main>
	</div>
</body>
</html>