package com.sist.dao;
import java.util.*;
// jsp 파일에서 분리시킴 => 최대한 html만 남아있도록 함 
// Model => 자바
//view => html
import javax.servlet.http.HttpServletRequest;

public class EmpModel {
	public void empListData(HttpServletRequest request)
	{
		EmpDAO dao=new EmpDAO();
		List<EmpVO> list=dao.empListData();
		request.setAttribute("list", list); // 추가 용도
	}
}
