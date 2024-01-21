package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String handleRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		return "board/insert.jsp";
	}

}
