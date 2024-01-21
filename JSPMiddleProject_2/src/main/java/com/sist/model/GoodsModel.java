package com.sist.model;
// 요청을 받아서 => 요청 처리 후에 결과값을 request , session에 실어서 JSP로 넘겨준다
// 브라우저 연결
/*
 * 	   request
 * 	JSP => Model => DAO
 * 		<=		 <=
 *     request: 결과값을 추가해서 전송
 *               setAttribute
 *  =================================> Call By Reference
 *  
 *  ==> all <% %> 안에 들어갔던 코딩
 */
/*
 * 	JSP : 요청 <a> <form> => GET / POST
 * 			=> 요청 처리 결과를 받아서 출력만 담당 => View (기능 X)
 * 			   ===========================
 *  java => 소스코딩 볼 수 없음 => 보안 뛰어남 / 이식성이 좋다 (운영체제 호환성) / 재사용이 가능하다
 *    웹에서 전송하는 내용  => JSP/Servlet (일반 자바는 사용이 불가)
 *    		request             request         
 *  	JSP ======= Controller ========= Model <=====> DAO 
 *  					|		request에 담는다
 *  				JSP를 forward
 *  		요청 => 해당 model 찾기
 *    = Controller : HTMl / JAVA => 연결하는 역할 (Servlet)
 *    = Model : 요청처리 => request / session에 담아서 전송
 *    = DAO : 오라클 연동
 *    = VO : 오라클에서 데이터 읽기 => Record단위 => row 단위로 읽어온다 => 데이터를 묶어서 한번에 저장 => list에 담기
 *    
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.dao.*;
public class GoodsModel {
	public void goodsListData(HttpServletRequest request)
	{
		//type,page => <% %>
		String type=request.getParameter("type");
		if(type==null)
			type="1";
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		// 페이지 지정
		int curpage=Integer.parseInt(page);
		// 페이지에 해당되는 목록
		GoodsDAO dao=new GoodsDAO();
		List<GoodsVO> list=dao.goodsListData(curpage, Integer.parseInt(type));
		int totalpage=dao.totalPage(Integer.parseInt(type));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
		{
			endPage=totalpage;
		}
	
		
		// JSP에서 출력할 데이터를 전송 
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("type", type);
	}
	public void goodDetailData(HttpServletRequest request)
	{
		// 요청값을 받는다 
		String table=request.getParameter("table"); // DAO => type 이랑 동일 => 테이블 번호
		String no=request.getParameter("no"); // 상품번호
		String type=request.getParameter("type"); // 화면변경
		// 요청에 해당되는 데이터 베이스 값을 가지고온다
		GoodsDAO dao=new GoodsDAO();
		GoodsVO vo=dao.goodsDetailData(Integer.parseInt(table), Integer.parseInt(no));
		// request에 담아준다 
		request.setAttribute("type", table);
		request.setAttribute("vo", vo);
		// ======= detail.jsp
	}
	
}
