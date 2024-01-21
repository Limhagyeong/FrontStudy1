package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;

import java.io.PrintWriter;
import java.util.*;
import com.sist.vo.*;

/*	
 * 	사용자 요청		*.do
 *  ========   --- DispathcerServlet 
 *    .do      호출	|
 *    				1) Modelclass 찾아주는 역할 ==> XML
 *    				2) 해당 메소드 찾아주는 역할
 *    			       =======
 *    					=> 구분자 (어노테이션)
 *    				2-1) 메소드 호출 => invoke()
 *    				2-2) request를 받아온다
 *    					 ======= 받아서 값을 넣을 때 => setAttribute
 *   				2-3) request를 해당 jsp로 전송
 *   					 ====================== > forward(request, response를 동시에 넘겨줌)
 *    => DAO (DBCP) => MyBatis
 *    => FRONT => AJAX , JQUETY => VUEJS
 *    (jsp는 바뀌지않음)
 *    	1. hosting
 *    -------------------------------------------
 *    => MyBatis(Oracle) => JPA (MySQL)
 *    => VUEJS => react, redux
 *    => spring => spring-Boot
 *    =====================================
 *    => 1. ThymeLeaf
 *    => 2. React
 *    => 3. Redux => React-Query
 *    ===================================== 서버를 개별적으로
 *    => 통합 (MSA => SpringCloud)
 */
//String[] guList = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
//		"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
//		"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
//		"강동구" };
public class FoodModel {
	private String[] guList = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
			"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
			"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
			"강동구" };
@RequestMapping("food/location.do")
	public String foodList(HttpServletRequest request, HttpServletResponse response)
	{
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		String gu=request.getParameter("gu");
		if(gu==null)
			gu="4";
		String address=guList[Integer.parseInt(gu)];
		
//		String address=request.getParameter("address");
//		if(address==null)
//			address="마포구";
		
		
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodFindData(curpage,address );
		int totalpage=dao.foodFindTotalPage(address);
		
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
		{
			endpage=totalpage;
		}
		//=> main.jsp에 include되는 파일 지정
		request.setAttribute("curpage", curpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("gu", gu);
		request.setAttribute("address", address);
		CommonsModel.commonsFooterData(request);
		request.setAttribute("main_jsp", "../food/location.jsp");
		return "../main/main.jsp";
	}
@RequestMapping("food/location_detail.do")
public void foodFindDetailData(HttpServletRequest request, HttpServletResponse response)
{
	String fno=request.getParameter("fno");
	FoodDAO dao=FoodDAO.newInstance();
	FoodVO vo=dao.foodFindDetail(Integer.parseInt(fno));
	
	JSONObject obj=new JSONObject();
	obj.put("poster", vo.getPoster());
	obj.put("name", vo.getName());
	obj.put("score", vo.getScore());
	obj.put("phone", vo.getPhone());
	obj.put("address", vo.getAddress());
	obj.put("time", vo.getTime());
	obj.put("theme", vo.getTheme());
	obj.put("price", vo.getPrice());
	obj.put("type", vo.getType());
	obj.put("seat", vo.getSeat());
	obj.put("content", vo.getContent());
	
	// 전송
	try
	{
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.write(obj.toString());
	}catch(Exception ex) {}

	
}
@RequestMapping("food/list.do")
public String foodListData(HttpServletRequest request, HttpServletResponse response)
{
	

	String page=request.getParameter("page");
	if(page==null)
		page="1";
	int curpage=Integer.parseInt(page);

	
//	String address=request.getParameter("address");
//	if(address==null)
//		address="마포구";
	
	
	FoodDAO dao=FoodDAO.newInstance();
	List<FoodVO> list=dao.foodListData(curpage);
	
	int totalpage=dao.foodTotalPage();
	
	final int BLOCK=10;
	int startpage=((curpage-1)/BLOCK*BLOCK)+1;
	int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	if(endpage>totalpage)
	{
		endpage=totalpage;
	}
	//=> main.jsp에 include되는 파일 지정
	request.setAttribute("curpage", curpage);
	request.setAttribute("startpage", startpage);
	request.setAttribute("endpage", endpage);
	request.setAttribute("totalpage", totalpage);
	request.setAttribute("list", list);
	CommonsModel.commonsFooterData(request);
	request.setAttribute("main_jsp", "../food/list.jsp");
	return "../main/main.jsp";
}
@RequestMapping("food/detail.do")
public String foodListDetailData(HttpServletRequest request, HttpServletResponse response)
{
	
	String fno=request.getParameter("fno");
	FoodDAO dao=FoodDAO.newInstance();
	FoodVO vo=dao.foodFindDetail(Integer.parseInt(fno));
	vo.setFno(Integer.parseInt(fno));
	
	request.setAttribute("vo", vo);
	request.setAttribute("main_jsp", "../food/detail.jsp");
	CommonsModel.commonsFooterData(request);
	return "../main/main.jsp";
}
@RequestMapping("food/food_before_detail.do") // 쿠키 먼저 저장 (동시에 수행할수없음)
public String foodBeforeDetailData(HttpServletRequest request, HttpServletResponse response)
{
	String fno=request.getParameter("fno");
	
	Cookie cookie=new Cookie("food_"+fno, fno);
	cookie.setPath("/");
	cookie.setMaxAge(60*60*24);
	response.addCookie(cookie);
	
	return "redirect:../food/food_detail.do?fno="+fno;
}
@RequestMapping("food/food_detail.do") // html로 값을 보냄
public String food_Detail(HttpServletRequest request, HttpServletResponse response)
{
	String fno=request.getParameter("fno");
	FoodDAO dao=FoodDAO.newInstance();
	FoodVO vo=dao.foodDetail(Integer.parseInt(fno));
	String addr=vo.getAddress();
	String addr1=addr.substring(addr.indexOf(" ")+1);
	String addr2=addr1.substring(0,addr1.indexOf(" "));
	System.out.println(addr2.trim());
	request.setAttribute("addr", addr2.trim());
	request.setAttribute("vo", vo);
	request.setAttribute("main_jsp", "../food/food_detail.jsp");
	return "../main/main.jsp";
}
}



