package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

public class StoreModel {
@RequestMapping("store/all.do")
public String store_all(HttpServletRequest request,HttpServletResponse response)
{
	// 데이터베이스 결과값 전송
	// 요청 => .do
	// include => .jsp
	String page=request.getParameter("page");
	if(page==null)
		page="1";
	int curpage=Integer.parseInt(page);
	GoodsDAO dao=GoodsDAO.newInstance();
	List<GoodsVO> list=dao.goodsAllListData(curpage);
	int totalpage=dao.goodsAllTotalPage();
	request.setAttribute("totalpage", totalpage);
	request.setAttribute("curpage", curpage);
	request.setAttribute("list", list); // list이름은 개별로 부여해줘야된다 => all list, best list, new list ...
	// 쿠키 데이터 전송
	Cookie[] cookies=request.getCookies();
	List<GoodsVO> cList=new ArrayList<GoodsVO>();
	if(cookies!=null)
	{
		for(int i=cookies.length-1;i>=0;i--)
		{
			if(cookies[i].getName().startsWith("goods_"))
			{
				String no=cookies[i].getValue();
				GoodsVO vo=dao.goodsCookieData(Integer.parseInt(no));
				cList.add(vo);
			}
		}
	}
	request.setAttribute("cList", cList);
	request.setAttribute("count", cList.size());
	request.setAttribute("store_jsp", "../store/all.jsp");
	request.setAttribute("main_jsp", "../store/store_main.jsp");
	return "../main/main.jsp";
}
@RequestMapping("store/best.do")
public String store_best(HttpServletRequest request,HttpServletResponse response)
{
	request.setAttribute("store_jsp", "../store/best.jsp");
	request.setAttribute("main_jsp", "../store/store_main.jsp");
	return "../main/main.jsp";
}
@RequestMapping("store/sp.do")
public String store_sp(HttpServletRequest request,HttpServletResponse response)
{
	request.setAttribute("store_jsp", "../store/sp.jsp");
	request.setAttribute("main_jsp", "../store/store_main.jsp");
	return "../main/main.jsp";
}
@RequestMapping("store/new.do")
public String store_new(HttpServletRequest request,HttpServletResponse response)
{
	request.setAttribute("store_jsp", "../store/new.jsp");
	request.setAttribute("main_jsp", "../store/store_main.jsp");
	return "../main/main.jsp";
}
@RequestMapping("store/detail_before.do") // 쿠키 저장 위치 => detail.do전에 실행
public String store_detail_before(HttpServletRequest request,HttpServletResponse response)
{
	String no=request.getParameter("no");
	Cookie cookie=new Cookie("goods_"+no, no);
	cookie.setPath("/");
	cookie.setMaxAge(60*60*24);
	// 브라우저로 전송
	response.addCookie(cookie);
	return "redirect:../store/detail.do?no="+no;
}
@RequestMapping("store/detail.do")
public String store_detail(HttpServletRequest request,HttpServletResponse response)
{
	String no=request.getParameter("no");
	GoodsDAO dao=new GoodsDAO();
	GoodsVO vo=dao.goodsAllDetailData(Integer.parseInt(no));
	String price=vo.getGoods_price();
	// 30,000원 => 30000
	price=price.replaceAll("[^0-9]", ""); // 숫자 제외 모든 문자를 공백으로 변환
	// 한글 제외 [^가-힣]
	request.setAttribute("no", no);
	request.setAttribute("vo", vo);
	request.setAttribute("store_jsp", "../store/detail.jsp");
	request.setAttribute("main_jsp", "../store/store_main.jsp");
	return "../main/main.jsp";
}
}
