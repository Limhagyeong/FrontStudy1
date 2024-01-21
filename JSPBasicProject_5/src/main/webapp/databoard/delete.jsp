<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.io.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"></jsp:useBean>
<%
	String no=request.getParameter("no");
	// DAO 연동
	DataBoardBean vo=dao.boardInfoData(Integer.parseInt(no));
	dao.boardDelete(Integer.parseInt(no));
	// 파일 삭제 
	try
	{
		if(vo.getFilesize()>0) // 파일이 존재하는 상태 
		{
			File file=new File("c:\\download\\"+vo.getFilename());
			file.delete();
		}
	}
	catch(Exception ex){}
	// list.jsp 이동
	response.sendRedirect("list.jsp");
%>
