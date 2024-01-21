<%@page import="java.awt.image.DataBufferDouble"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*"%>
    <%
    	try
   		{
    		request.setCharacterEncoding("UTF-8");
    		String fn=request.getParameter("fn");
    		File file=new File("c:\\download\\"+fn);
    		response.setContentLength((int)file.length());
    		// 파일 전송 => header전송 => 파일명, 파일 크기 (클라이언트에서 파일 업로드)
    		response.setHeader("Content-Disposition", "attachment;filename="
    		+URLEncoder.encode(fn,"UTF-8"));
    		
    		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    		// 서버에서 파일 읽기
    		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
    		//												           사용자의 다운로드 폴더 => 사용자 폴더에 파일을 복사떠줌
    	    // 멀티 => Spring ==> 웹흐름 , SQL => 기능 => 변별력 
    		// 클라이언트에게 파일 보내기
    		byte[] buffer=new byte[1024];
    		int i=0;
    		while((i=bis.read(buffer, 0, 1024))!=-1) // 파일 복사 뜨기
    		{
    			bos.write(buffer,0,i);
    		}
    		bos.close();
    		bis.close();
    		out.clear();
    		out=pageContext.pushBody();
    		// out의 원래 기능으로 복귀 => HTML 출력용
    	}
    	catch(Exception ex){}
    %>
