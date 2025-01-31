<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.sist.vo.*" %>
<%@ page import="java.util.*" %>
<%--
	fs=all&mode=2&ss=
	=> ss!null => ss=""
 --%>
 <jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%  
	String fs=request.getParameter("fs");
	String ss=request.getParameter("ss");
	String strpage=request.getParameter("page");
	
	if(strpage==null)
		strpage="1";
	int curpage=Integer.parseInt(strpage);
	
	List<FoodVO> list=new ArrayList<FoodVO>();
	int totalpage=0;
	if(ss==null || ss.equals(""))
	{
		fs="all";
	}
	if(fs.equals("all"))
	{
		list=dao.foodAllData(curpage);
		totalpage=dao.foodAlltotalpage();
	}
	else
		{
			list=dao.foodfindData(curpage, fs, ss);
			totalpage=dao.foodFindtotalpage(fs, ss);
		}
	final int BLOCK=10;
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endPage>totalpage)
		endPage=totalpage;
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">
          <form method="post" action="../main/main.jsp" class="inline">
          <select name="fs">
          	<option value="all" <%=fs.equals("all")?"selected":"" %>>전체</option>
          	<option value="name" <%=fs.equals("name")?"selected":"" %>>업체명</option>
          	<option value="address" <%=fs.equals("address")?"selected":"" %>>주소</option>
          </select>
          <input type=hidden name=mode value=2>
          <input type="text" name=ss size=15 value="<%= ss==null?"":ss %>">
          <button>검색</button>
          </form>
          </header>
          <ul class="nospace clear">
          <%
          	for(int i=0;i<list.size();i++)
          	{
          		FoodVO vo=list.get(i);
          %>
            <li class="one_quarter <%= i%4==0?"first":"" %>"><a href="#"><img src="https://www.menupan.com<%=vo.getPoster() %>" title="<%=vo.getName() %>"></a></li>
         <%
          	}
         %>
          </ul>
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
        <%
        	if(startPage>1)
        	{
        %>
         		 <li><a href="../main/main.jsp?mode=2&page=<%= startPage-1%>&ss=<%=ss%>&fs=<%=fs%>">&laquo; Previous</a></li>
          <%
        	}
          %>
          <%
          	for(int i=startPage;i<=endPage;i++)
          	{
          %>
          <li <%= curpage==i?"class=current":"" %>><a href="../main/main.jsp?mode=2&page=<%=i%>&ss=<%=ss%>&fs=<%=fs%>"><%=i %></a></li>
          <%
          	}
          %>
          <%
          	if(endPage<totalpage)
          	{
          %>
          <li><a href="../main/main.jsp?mode=2&page=<%= endPage+1%>&ss=<%=ss%>&fs=<%=fs%>">Next &raquo;</a></li>
          <%
          	}
          %>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>