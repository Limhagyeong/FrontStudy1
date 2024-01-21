<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	211p => 자바빈즈 => VO : 데이터를 모아서 브라우저로 한번에 전송 목적
			======
			   JSP : Bean , MyBatis : DTO , Spring : VO
			|액션태그
			 <jsp:useBean> : 객체생성
			 => BoardVO vo=new BoardVO();
			 	<jsp:Bean id="vo" class="BoardVO">
			 	
			 <jsp:setProperty> : set 메소드 호출
			 	<jsp:setProperty name="vo" property="name" value="홍길동">
			 		=> vo.setName("홍길동"); 
			 
			 <jsp:getProperty> : get 메소드 호출
			 	<jsp:getProperty name="vo" property="name">
			 		=> <%= vo.getName()%>
			 		
			 ***<jsp:Include> : 특정 위치에 다른 JSP를 첨부
			 	<jsp:include page="">
			 	=> pageContext.include()
			 
			 <jsp:param> : 데이터 전송 => 팝업창 띄우기
			 
	213p : 자바 빈즈 만들기
			1) 데이터를 저장할 변수 설정 : 캡슐화 (private 설정)
				=> private 으로 설정이 되면 다른 클래스에서 사용이 불가 
				=> getter setter (변수의 기능을 부여하는 메소드를 만든다)
				   메모리 읽기 메모리 저장
				   
		   2) getter setter 
		   		=> set변수명() => 변수명의 첫자는 대문자
		   		   setName()
		   		=> get변수명() => 변수명의 첫자는 대문자
		   		   getName()
		   		=> boolean이면
		   			private boolean login;
		   			=> setter : isLogin()
		   			=> getter : getLogin()
		   		=> 여기서 사용하는 모든 getter setter 는 다른 클래스와 호환 필요
		   				=====> 반드시 public
		   		=> 데이터 보호 => 메소드 이용해서 처리
		   		=> JSP => MemberBean , BoardBean
			 	
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