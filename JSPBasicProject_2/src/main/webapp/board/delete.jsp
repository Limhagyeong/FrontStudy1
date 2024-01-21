<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	//1. 요청값을 받는다
	String no=request.getParameter("no");
	String curpage=request.getParameter("page");
	String pwd=request.getParameter("pwd");
	//2. 데이터베이스 처리
	BoardDAO dao=BoardDAO.newInstance();
	// 삭제처리
	boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
	if(bCheck==true)
	{
		response.sendRedirect("list.jsp?no="+no+"?page="+curpage);
	}
	else
	{
		%>
			<script>
			alert("비밀번호가 틀렸습니다");
			history.back();
			</script>
		<%
	}
	//3. 화면 이동 => 기존의 화면 이동(list.jsp) / 화면 출력 ==> 메모리 해제와 동시에 생성 
	/*
		list.jsp			detail.jsp
		=======				===========
		|insert.jsp			| reply.jsp => list.jsp
		|detail.jsp			| update.jsp => detail.jsp
							| delete.jsp => list.jsp
							
		insert => list.jsp
	*/	
	
%>