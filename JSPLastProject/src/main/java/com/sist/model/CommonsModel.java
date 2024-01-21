package com.sist.model;
import java.util.*;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.manager.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
import com.sist.vo.*;
// 공통모듈 => Spring (AOP) => 메소드 등록
// 시간 => setInterval() => task

public class CommonsModel {
	public static void commonsFooterData(HttpServletRequest request)
	{
		// 1. 공지사항 => hit가 많은 
		NoticeDAO ndao=NoticeDAO.newInstance();
		List<NoticeVO>noList=ndao.noticeTop7();
		request.setAttribute("noList", noList);
		// 2. 맛집 뉴스
		List<NewsVO> nList=NewsManager.newsSearchData("맛집");
		request.setAttribute("nList", nList);
		// 3. 인기맛집 => hit가 많은
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> fList=dao.foodTop7();
		request.setAttribute("fList", fList);
	}
}
