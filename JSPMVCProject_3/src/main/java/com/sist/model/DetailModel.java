package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import javax.servlet.http.HttpServletRequest;

public class DetailModel implements Model {

	@Override
	public String handleRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		// => DAO 이동 
		BoardDAO dao=BoardDAO.newInstance();
		boardVO vo=dao.boardDetail(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		return "board/detail.jsp";
	}

}
