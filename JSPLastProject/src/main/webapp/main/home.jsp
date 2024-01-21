<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <div id="slider" class="clear"> 
   
    <div class="flexslider basicslider">
      <ul class="slides">
      <c:forEach var="i" begin="1" end="5">
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/${i }.jpg" style="width:978px;height:400px"></a></li>
      </c:forEach>
        
      </ul>
    </div>
    
  </div>
</div>

<div class="wrapper row3">
  <main class="container clear"> 
  
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">Lorem Ipsum Dolor</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
   
    <h2 class="sectiontitle">인기 한식 맛집</h2>
   
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
      <c:forEach var="vo" items="${alist }" >
        <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name }</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 양식 맛집</h2>
   
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
      <c:forEach var="vo" items="${blist }" >
        <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name }</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 중식 맛집</h2>
   
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
      <c:forEach var="vo" items="${clist }" >
        <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name }</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">인기 일식 맛집</h2>
   
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
      <c:forEach var="vo" items="${dlist }" >
        <li>
          <figure><img class="radius-10 btmspace-10" src="${vo.poster }">
            <figcaption><a href="../food/food_before_detail.do?fno=${vo.fno }">${vo.name }</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
  
    <h2 class="sectiontitle">최근 방문 맛집</h2>
   <c:forEach var="kvo" items="${kList }" varStatus="s">
   <c:if test="${s.index<9 }">
   <div class="inline">
  <img class="radius-10" src="${kvo.poster }" style="width: 100px;height: 100px;" title="${kvo.name }">
 </div>
  </c:if>
   </c:forEach>
  
  
    
    <div class="clear"></div>
  </main>
</div>
</body>
</html>