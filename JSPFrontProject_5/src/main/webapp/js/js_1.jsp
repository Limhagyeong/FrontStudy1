<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. HTML 태그 선택 => 제어
		====== 객체 모엘 (태그 : 클래스, 속성: 멤버변수)
		=> CSS의 선택자
		=> 1) 속성값 변경 2) 스타일 변경 3) 이벤트 등록 
	2. 이벤트 : 사용자가 행위를 했을 때 처리
			  ========== mouse, keyboard...
	3. 브라우저 내장객체 
	   window / location / history / document
	4. 내장 객체
	   String / date / number / math
	5. ====================================
		라이브러리 => JQuery : 자바스크립트 기본 문법을 적용
	
	341p
	문서 객체 선택
	===========
	태그를 객체로 변환해서 사용
	태그 선택하는 방법 
	============== $(JQuery) , e.target(React) , $ref(VueJS)
	자바스크립트 
	 => document.getElementById(ID명)
	 ***=> document.querySelector("css선택자")
	    ====================>$("css선택자")
	    ==> 태그 한개 설정
	 => document.getElementsClassName("클래스명")
	 ***=> document.querySelectorAll("css선택자")
	 => document.getElementByTagName("태그명")
	 => document.getElementByName("name속성값")
	 =============================================
	 createElement("태그명") => 태그생성
	 createTextNode()=> 태그와 태그 사이 값을 설정
	 ***appendChild() => 태그 안에 다른 태그를 첨부
	 	=> $("css선택자").append()
	 	   v-html
	 setAttribute() => 속성 추가
	  	=> $("css선택자").attr(속성명,값)
	  	   v-model
	 getAttribute() => 속성값 읽기
	 	=>$("css선택자").attr(속성명)
	 ==> 태그와 태그 사이에 데이터 추가
	     =======================
	     html을 추가 => innerHTML
	     문자만 추가 => textContent = '<h1>aaa</h1>'
	
	1. 태그 선택
	   => getElementById(id명)
	   		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let div=document.getElementById("box"); // 태그 자체를 가지고온다 
	//div.textContent="<font color=red>취소</font>";
	//=> $('#box').text()
	div.innerHTML="<font color=red>취소</font>";
	//=> $('#box').html()
}
</script>
</head>
<body>
	<div id="box">수정</div>
</body>
</html>