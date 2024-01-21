<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	1. 내장 객체 얻기
    	   getRequest(), getResponse() , getOut()
    	   getSession(), getPage(), getException()
    	   application => getServletContext()
    	   => 사용 빈도가 거의 없다 (99.9999999)
    	   
    	   request.getParameter()
    	   pageContext.getRequest().getParameter()
    	2. 웹 흐름 제어
    		include(), ***forward() => 파일마다 request를 공유한다
    		pageContext.include() ==> X
    		 => <jsp:include>
    		 => 
    		 	1. <%@ include file=""%> : 정적
    		 		=> file에는 반드시 file명을 설정한다
    		 			menu / footer
    		 	2. <jsp:include page=""> : 동적
    		 	    => 내용 출력시에 변경
    		 	       page=변수명
    		 	       
    		 	 => JSP 안에 특정 위치에 다른 JSP를 포함
    		 	 a.jsp
    		 	 <html>
    		 	 	<body>
    		 	 		<%
    		 	 			int a=10;
    		 	 		%>
    		 	 		<h1><%= a%><h1>
    		 	 	</body>
    		 	 </html>
    		 ==========================================
    		     <html>
    		     <body>
    		     	<h1>10</h1>
    		     =======================	
    		     	<html>
    		     <body>
    		     	<h1>100</h1>
    		     </body>
    		     </html> // jsp:include
    		     =========================
    		     </body>
    		     </html>
    		===========================================
    			b.jsp
    			<html>
    			<body>
    			 <%
    			 	int a=100;
    			 %>
    			 <h1><%=a%></h1>
    			 ========================================
    			 <%@ include file="a.jsp"%>
    			 <html>
    		 	 	<body>
    		 	 		<%
    		 	 			int a=10;
    		 	 		%>
    		 	 		<h1><%= a%><h1>
    		 	 	</body>
    		 	 </html> // => a변수가 같기 때문에 오류남 (소스 자체를 합쳐버림!) : 파일을 합쳐서 컴파일 하는 형식
    		 	 ===========================================
    		 	 <jsp:include page="a.jsps"> => 개별 컴파일 후 컴파일 결과를 합쳐버림 => 오류 발생 X
    			</body>
    			</html>
     		=============================================
     			 <html>
    		     <body>
    		     	<h1>100</h1>
    		     </body>
    		     </html>
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