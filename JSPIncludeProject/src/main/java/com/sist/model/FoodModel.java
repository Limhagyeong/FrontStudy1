package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

public class FoodModel {
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request,HttpServletResponse response)
	{	request.setAttribute("main_jsp", "../food/list.jsp"); 
	// value값으로 인클루드할 파일명을 준다
	// <jsp:include page="${main_jsp }"></jsp:include>
		return "../main/main.jsp";
	}
	@RequestMapping("food/find.do")
	public String food_find(HttpServletRequest request,HttpServletResponse response)
	{	request.setAttribute("main_jsp", "../food/find.jsp"); 
	// value값으로 인클루드할 파일명을 준다
	// <jsp:include page="${main_jsp }"></jsp:include>
		return "../main/main.jsp";
	}

}
