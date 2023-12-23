<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. 선택자 => JavaScript
	2. 글자 관련 
	3. margin / padding / border
	4. background
	5. position 
	    ==> 배치 => top bottom left right
	    ==> fixed
	    ==> relative
	6. overflow
	===============================================
	css는 약간의 수정
	=> 퍼블리셔
	
	HTML / CSS => 화면 UI
	JavaScript => UI 제어
	Java => 데이터관리
	   		오라클 => 자바 => HTMl / JS
	오라클 => 데이터 저장 공간
	======================= 통합 (spring)
	Spring-Boot <==> Front
	============= MSA 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
	margin: 0px;
	padding: 0px;
}
body{
	width: 960px;
	margin: 0px auto;
	overflow: hidden;
}
#nav{
	width: 300px;
	float: left;
}
#section{
	width: 660px;
	float: right;
}
li {
	list-style:none;
}
@media screen and (max-width:768px){
	body{width:auto}
	#nav{width:auto;float:none}
	#section{width:auto;float:none}
}
</style>
</head>
<body>
	<div id="nav">
		<ul>
		<li>홈</li>
		<li>뉴스 1</li>
		<li>뉴스 2</li>
		</ul>
	</div>
	<div id="section">
	위르겐 클린스만 한국 남자 축구 대표팀 감독이 아시아축구연맹(AFC) 아시안컵 대비 국내 소집 훈련에서 새로운 스트라이커를 점검하지 않기로 했다.

	대한축구연맹은 18일 보도자료를 통해 내년 1월 카타르에서 개막하는 2023 AFC 아시안컵 대비 국내 소집 훈련 명단을 발표했다. 
	이번 명단은 김영권, 설영우, 송범근 등 시즌이 종료된 K리그, J리그 선수들과 조규성, 이재성, 황인범 등 휴식기를 갖는 
	유럽 리거 중 소집 가능한 선수들로 구성됐다. 시즌 전반부 빡빡한 일정을 소화한 김민재는 대회를 앞두고 휴식에 초점을 맞출 예정이다.
	</div>
</body>
</html>