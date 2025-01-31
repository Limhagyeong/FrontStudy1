<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
var IMP = window.IMP; // 생략 가능
IMP.init("imp34378262"); // 예: imp00000000
function requestPay() {
	console.log('clicked');
  // IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({
	    pg : 'html5_inicis', // version 1.1.0부터 지원.
	    
	        /*
	            'kakao':카카오페이,
	            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
	            'nice':나이스,
	            'jtnet':jtnet,
	            'uplus':LG유플러스
	        */
	    pay_method : 'card', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : $('#buyBtn').attr("data-title"),
	    amount : $('#buyBtn').attr("data-price"),
	    buyer_email : 'iamport@siot.do',
	    buyer_name : '구매자이름',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456',
	    app_scheme : 'iamporttest' //in app browser결제에서만 사용 
	}, function(rsp) {
	    if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	        
	        let no=$('buyBtn').attr("data-no")
	        location.href="../goods/cart_buy.do?no="+no;
	    }
		})
	}
$(function() {
	$('#buyBtn').click(function(){
		requestPay()
	})
})
</script>
</head>
<body>
<h3 class="text-center">장바구니 목록</h3>
<table class="table">
<tr>
<th class="text-center">번호</th>
<th class="text-center"></th>
<th class="text-center">상품명</th>
<th class="text-center">가격</th>
<th class="text-center">수량</th>
<th class="text-center">총구매가격</th>
<th class="text-center"></th>
</tr>
<c:forEach var="vo" items="${list }">
<tr>
<td class="text-center">${vo.cart_no }</td>
<td class="text-center">
<img src="${vo.gvo.goods_poster }" style="width: 30px; height: 30px;">
</td>
<td class="text-center">${vo.gvo.goods_name }</td>
<td class="text-center">${vo.gvo.goods_price }</td>
<td class="text-center">${vo.amount }</td>
<td class="text-center">${vo.price*vo.amount}</td>
<td class="text-center">
<input type="button" class="btn btn-am btn-success" value="구매" id="buyBtn"
data-title="${vo.gvo.goods_name }" data-price="${vo.price }" data-no="${vo.cart_no }">

<a href="../goods/cart_cancel.do?no=${vo.cart_no }" class="btn btn-am btn-primary">취소</a>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>