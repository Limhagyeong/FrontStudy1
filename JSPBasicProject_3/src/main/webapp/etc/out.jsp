<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	내장 객체 => 미리 객체를 생성한 후에 사용이 가능하게 만든 것
    	=======
    	1. 객체명 
    	2. 클래스 
    	3. 용도 
    	4. 주요 메소드
		-------------------------------------------------------------
		request
		클래스명 : HttpServletRequest
		용도 : 클라이언트의 요청 값을 묶어서 저장하고 있다
			  클라이언트의 정보 : IP
			  세션이나 쿠키 생성 
			  한글 변환 (디코딩)
		주요메소드
		    getParameter(key명) => 단일값 받기
		    getParameterValuse(key명) => 다중값 받기(check box)
		    setCharacterEncoding() => 한글 변환
		    ==================================== 전송 : 인코딩, 수신 : 디코딩
		    getRemoteAddr() => IP
		    getCookie()
		    getSession()
		    ====================================
		    서버 정보
			getRequestURL()
			getRequestURI()
			getContextPath()
			======================URL======================
			http://localhost/JSPBasicProject_3/etc/out.jsp
				서버정보		==================URI==========
							================= ContextPath
			추가 정보
			setAttribute() => request에 데이터 추가해서 넘겨주는 경우
			getAttribute() => 추가된 데이터 읽기
			=====================================
			
			Model 1 => 
			JSP <=> JSP
			
			Middle
			
			MV => Java / HTML 분리 (model:java view: jsp) => EL / JSTL
			
			Model 2	    
			MVC ==> MVVC => MVP ==> MVVP
			    ===> Spring
			    
			response
			========
			클래스명 : HttpServletResponse
			용도 : HTML, Cookie 전송
				  화면 변경
				  헤더 작성
		    주요 메소드 :
		    			response.setContentType("")
		    			  => text/html
		    			  => text/xml
		    			  => text/plain
		    			addCookie() => 해당 브라우저로 전송 
		    			sendRedirect(파일명)=> GET방식만 사용
		    			setHeader() => 다운로드
		   ===================================================
		   out : JspWriter
		   		 => 출력 buffer => HTML을 출력해서 저장하는 공간
		   		  				  ======================
		   		  				  | 요청한 브라우저에서 공간의 HTML을 읽어서 출력
		   		  				  | 자동으로 비워준다 => 브라우저 한개당 한개만 생성
		   		  				  | 기본 8kb
		   		 => 화면 출력
		   		    println() / print()
		   		 => 버퍼의 크기
		   		 	getBufferSize()
		   		 => 남아있는 버퍼 확인
		   		    getRemaining()
		   		 => <%= %> => ${} 
		   		     JSP     
		   		 => 한번 사용 => 다운로드!
		   application
		   pageContext
		   exception
		   config
		   page
	--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>out:JspWriter:버퍼관리</h1>
	1. 전체버퍼의 크기 확인: <%= out.getBufferSize() %> <%-- 8*1024 --%>
	<br>
	2. 남아있는 버퍼의 크기 확인 : <%= out.getRemaining() %>
	<br>
	3. 사용중인 버퍼 크기 확인 : <%=out.getBufferSize()-out.getRemaining() %>
	<br>
	4. &lt;=&gt; 대체: out.println() / out.write()
		=> 복잡한 HTML을 만들 경우
		<br>
		<%
			out.println("<h1>OUT객체</h1>");
			out.print("<h1>OUT객체</h1>");
			out.write("<h1>OUT객체</h1>");
		%>
	
</body>
</html>