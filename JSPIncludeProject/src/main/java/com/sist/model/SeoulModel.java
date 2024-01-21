package com.sist.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.SeoulVO;

public class SeoulModel {
	@RequestMapping("seoul/location.do")
	public String seoul_location(HttpServletRequest request,HttpServletResponse response)
	{	
		// 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		// DB연동
		
		SeoulDAO dao=SeoulDAO.newInstance();
		System.out.println("seoul-dao="+dao);
		List<SeoulVO> list=dao.seoulLocationListData(curpage,"seoul_nature");
		int totalpage=dao.totalPage("seoul_nature");
		
//		final int BLOCK=10;
//		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
//		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
//		
//		if(endPage>totalpage)
//		{
//			endPage=totalpage;
//		}
		
		//DB연동
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("main_jsp", "../seoul/location.jsp"); 
		return "../main/main.jsp";
	}
	
	@RequestMapping("seoul/nature.do")
	public String seoul_nature(HttpServletRequest request,HttpServletResponse response)
	{	
		// 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		// DB연동
		
		SeoulDAO dao=SeoulDAO.newInstance();
		List<SeoulVO> list=dao.seoulLocationListData(curpage,"seoul_shop");
		int totalpage=dao.totalPage("seoul_shop");
		
//		final int BLOCK=10;
//		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
//		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
//		
//		if(endPage>totalpage)
//		{
//			endPage=totalpage;
//		}
		
		//DB연동
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("main_jsp", "../seoul/shop.jsp"); 
		return "../main/main.jsp";
	}
	@RequestMapping("seoul/shop.do")
	public String seoul_shop(HttpServletRequest request,HttpServletResponse response)
	{	
		// 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		// DB연동
		
		SeoulDAO dao=SeoulDAO.newInstance();
		List<SeoulVO> list=dao.seoulLocationListData(curpage,"seoul_shop");
		int totalpage=dao.totalPage("seoul_shop");
		
//		final int BLOCK=10;
//		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
//		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
//		
//		if(endPage>totalpage)
//		{
//			endPage=totalpage;
//		}
		
		//DB연동
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("main_jsp", "../seoul/shop.jsp"); 
		return "../main/main.jsp";
	}
}
