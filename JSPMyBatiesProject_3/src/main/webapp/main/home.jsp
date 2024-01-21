<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="text-center">
				<a href="../main/main.do?type=1" class="btn btn-lg btn-info">한식</a> 
				<a href="../main/main.do?type=2" class="btn btn-lg btn-danger">중식</a> 
				<a href="../main/main.do?type=3" class="btn btn-lg btn-success">일식</a>
				 <a href="../main/main.do?type=4" class="btn btn-lg btn-warning">양식</a>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="#"> <img
							src="https://www.menupan.com${vo.poster }" style="width: 100%">
							<div class="caption">
								<p>${vo.name }</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
		<div class="text-center">
				<ul class="pagination">
				<c:if test="${startpage>1 }">
					<li><a href="../main/main.do?page=${startpage-1 }&type=${type }">&lt;</a></li>	
					</c:if>
					<c:forEach var="i" begin="${startpage }" end="${endpage }">
					<li "${curpage==i?"class=active":"" }">
					<a href="../main/main.do?page=${i }&type=${type}">${i }</a></li>
					</c:forEach>
					<c:if test="${endpage<totalpage }">
					<li><a href="../main/main.do?page=${endpage+1 }&type=${type }">&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>