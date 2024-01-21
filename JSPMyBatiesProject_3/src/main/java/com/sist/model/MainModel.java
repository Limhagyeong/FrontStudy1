package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

public class MainModel {

   @RequestMapping("main/main.do")
   public String main_main(HttpServletRequest request,HttpServletResponse response) {
      
      String page=request.getParameter("page");
      if (page==null) {
         page="1";
      }
      int curpage=Integer.parseInt(page);
      
      String type=request.getParameter("type");
      
      String [] types = {"","한식","양식","중식","일식"};
      if(type==null) {
         type="1";
      
      }
      int rowsize=20;
      int start=(curpage*rowsize)-(rowsize-1);
      int end=curpage*rowsize;
      
      Map map=new HashMap();
      map.put("type", types[Integer.parseInt(type)]);
      map.put("start", start);
      map.put("end",end);
      
      
      List<FoodVO>list=FoodDAO.foodListData(map);
      for(FoodVO vo:list) {
         String name=vo.getName();
         if(name.length()>20) {
            name=name.substring(0,15)+"...";
            vo.setName(name);
         }
      }
      
      int totalpage=FoodDAO.foodTotalPage(map);
      
      final int BLOCK=10;
      int startPage=((curpage-1)/BLOCK*BLOCK)+1;
      int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
      if (endPage>totalpage) {
         endPage=totalpage;
      }
      request.setAttribute("curpage", curpage);
      request.setAttribute("totalpage", totalpage);
      request.setAttribute("startpage", startPage);
      request.setAttribute("endpage", endPage);
      request.setAttribute("list", list);
      request.setAttribute("type", type);
      
      request.setAttribute("main_jsp", "../main/home.jsp");
      return "../main/main.jsp";
   }
}