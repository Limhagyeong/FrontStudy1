package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.text.*;

import com.sist.vo.*;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class BoardModel {
@RequestMapping("board/list.do")
public String board_list(HttpServletRequest request, HttpServletResponse response)
{
	String page=request.getParameter("page");
	if(page==null)
		page="1";
	int curpage=Integer.parseInt(page);
	
	BoardDAO dao=BoardDAO.newInstance();
	List<BoardVO> list=dao.boardListData(curpage);
	
	int totalpage=(int)Math.ceil(dao.boardRowCount()/10.0);
	int count=dao.boardRowCount();
	count=count-((curpage*10)-10); // 게시물 삭제 시 정상 번호 들어옴
	
	
	request.setAttribute("count", count);
	request.setAttribute("curpage", curpage);
	request.setAttribute("totalpage", totalpage);
	request.setAttribute("list", list);
	request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	CommonsModel.commonsFooterData(request);
	request.setAttribute("main_jsp", "../board/list.jsp");
	return "../main/main.jsp";
}
@RequestMapping("board/insert.do")
public String boardInsert(HttpServletRequest request,HttpServletResponse response)
{
	
	request.setAttribute("main_jsp", "../board/insert.jsp");
	CommonsModel.commonsFooterData(request);
	return "../main/main.jsp";
}
@RequestMapping("board/insert_ok.do")
public String boardInsertOk(HttpServletRequest request,HttpServletResponse response)
{
	try {
		request.setCharacterEncoding("UTF-8");
	} catch (Exception e) {
		// TODO: handle exception
	}
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	BoardVO vo=new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	BoardDAO dao=BoardDAO.newInstance();
	dao.boardInset(vo);
	
	
	return "redirect:../board/list.do";
}
@RequestMapping("board/detail.do")
public String boardDetail(HttpServletRequest request,HttpServletResponse response)
{
	String no=request.getParameter("no");
	BoardDAO dao=BoardDAO.newInstance();
	BoardVO vo=dao.boardImfoData(Integer.parseInt(no),1);
	request.setAttribute("vo", vo);
	// => 댓글목록
	
	request.setAttribute("main_jsp", "../board/detail.jsp");
	CommonsModel.commonsFooterData(request);
	return "../main/main.jsp";
}
@RequestMapping("board/delete_ok.do")
public void boardDetail_ok(HttpServletRequest request,HttpServletResponse response)
{
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	BoardDAO dao=BoardDAO.newInstance();
	
	String res=dao.boardDelete(Integer.parseInt(no), pwd);
	try
	{
//		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.write(res);
	}catch(Exception ex) {}
	
}
@RequestMapping("board/update.do")
public String boardUpdate(HttpServletRequest request,HttpServletResponse response)
{
	String no=request.getParameter("no");
	BoardDAO dao=BoardDAO.newInstance();
	BoardVO vo=dao.boardImfoData(Integer.parseInt(no), 2); // 2번이면 조회수 증가 X
	request.setAttribute("vo", vo);
	request.setAttribute("main_jsp", "../board/update.jsp");
	CommonsModel.commonsFooterData(request);
	return "../main/main.jsp";
}
@RequestMapping("board/update_ok.do")
public void boardUpdate_ok(HttpServletRequest request,HttpServletResponse response)
{
	try
	{
	request.setCharacterEncoding("UTF-8");	
	}catch(Exception ex) {}
	
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	
	BoardVO vo=new BoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	BoardDAO dao=BoardDAO.newInstance();
	String res=dao.boardUpdate(vo);
//	BoardVO vo=dao.boardImfoData(Integer.parseInt(no), 2);

	try
	{
		PrintWriter out=response.getWriter();
		out.write("result");
		
		
	}catch(Exception ex) {}
}
}



