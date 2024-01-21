<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
 <%
 	SawonVO vo=new SawonVO();
 	vo.setName("홍길동");
 	vo.setDept("개발부");
 	// ${} => request, session
    request.setAttribute("vo", vo); // JSP로 데이터를 추가해서 전송 
    session.setAttribute("vo", vo);
    // 제한이 없다 
 	SawonVO svo=(SawonVO)request.getAttribute("vo");
 %>
<%--	
	 화면 출력 => 태그형 프로그램 제작 (자바 최소화)
	 <%= %> => ${} , <c:out value=""/>
	 		  === $는 Jquery 라이브러리 
	 let a=${name} => 
	 
	 ${출력물} => 자바의 변수가 아니다 
	   ==== request , session 
	 request.setAttribute("name","홍길동")
	 
	 <%=request.getAttribute("name") %> 
	 ${name}
	 	==== 키값
	 	
	 => getParameter() => ? , GET / POST : 사용자가 보내준 값
	    setAttribute() => request에 기존에 있는 데이터 외 다른 데이터를 추가해서 전송 
	    				  데이터베이스 ... 
	 => 새로 추가된 데이터를 읽어서 출력 
	 
	 => Param => getParameter("name")
	    ${param.name}
	    		==== 키값과 일치 
	 
	 1) 연산자 
	=====================================================
	${requestScope.name} => request.getAttribute("name");
				   ===== 키 
	${sessionScope.id] => session.getAttribute("id");
				   ===== 키 
	=> request,session 저장시에 Map형식으로 저장
	   ================ 키, 값
	   
	예) 키명이 같을 때
	    => request가 우선순위 
	   => sessionScope. 생략 불가
	
	   session.setAttribute("admin","1")
	    => sessiongetAttribute("admin")
	    => ${sessionScope.admin}
	   request.setAttribute("id","hong");
	   	=> request.getAttribute("id")
	   	           ============ getParameter()로 받으면 오류 
	   	=> ${requestScope.id}
	   	=> ${id} => requestScope. => 생략가능  
	  
	  ?id=admin&pwd=1234
	   => request.getParameter("id") => admin
	   	  request.getParameter("pwd") => 1234
	   => ${param.id} => admin
	      ${param.pwd} => 1234
	      
		bean => vo
	=> class Sawon
	{
		private int sabun;
		private String name;
		
		=> getter / setter
			getSabun() , setSabun()
			getName() , setName() 
	}
	Sawon vo=new Sawon();
	vo.setSabun(1); ==> getSabun
	vo.setName("홍길동"); ==> getName
	
	request.setAttribute("vo",vo); => 해당 JSP로 요청값 전송 
	=> Sawon vo=(Sawon)request.getAttribute("vo");
	             =================================
	             ${vo.getName()} => ${vo.name}
	             						 => getName()
	   vo.getName() / vo.getSabun()
	   
	  Model => 자바 (조립)
	  ==== DAO / VO / ... 자바로 코딩하는 모든 파일 
	       ======== 한개로 만들 수 있다  
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름: <%=svo.getName() %><br>
부서: <%=svo.getDept() %><br>
<h1>EL</h1>
이름 : ${vo.getName() },${vo.name }<br>
부서 : ${vo.getDept() },${vo.dept }<br>
이름 : ${sessionScope.vo.getName() },${sessionScope.vo.name }<br>
부서 : ${sessionScope.vo.getDept() },${sessionScope.vo.dept }<br>
</body>
</html>