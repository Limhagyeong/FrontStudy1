<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@page import="com.oreilly.servlet.*" %>
<%@page import="com.oreilly.servlet.multipart.*" %>
<%@page import="java.io.*" %>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<jsp:useBean id="bean" class="com.sist.dao.DataBoardBean"></jsp:useBean>
     <%-- 업로드 라이브러리 --%>
     <%-- 이미지 : relpath --%>
<%
 	request.setCharacterEncoding("UTF-8");

	// 1. 파일 업로드 지정
	String path="c:\\download";
	String enctype="UTF-8"; // 한글파일명
	int max=1024*1024*100; // 100MB 
	MultipartRequest mr=new MultipartRequest(request,path,max,enctype,
				new DefaultFileRenamePolicy());
	//				파일명이 같은 경우 => 파일명 뒤에 (1) (2) 자동으로 부여 => 저장
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	
	//mr.getFilesystemName("upload"); => 실제 저장된 파일명 (파일명 중복 불가)
	//mr.getOriginalFileName("upload"); => 사용자가 보낸 파일명 (파일명 중복 가능)
	String filename=mr.getFilesystemName("upload");
	
	bean.setName(name);
	bean.setSubject(subject);
	bean.setPwd(pwd);
	bean.setContent(content);
	if(filename==null) // 업로드가 안된 상태
	{
		bean.setFilename("");
		bean.setFilesize(0);
	}
	else // 업로드가 된 상태
	{
		bean.setFilename(filename); 
		File file=new File(path+"\\"+filename);
		bean.setFilesize((int)file.length());
	}
	
	dao.boardInsert(bean);
	
	response.sendRedirect("list.jsp");
 	
%>