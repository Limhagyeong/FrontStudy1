package com.stsi.temp;

public class MainClass_1 {
public static void main(String[] args) {
	String price="20,300원";
	price=price.replaceAll("[^0-9]", "");
	// 숫자 => [0-9] 알파벳 => [A-Za-z] 한글 => [가-힣]
	// ^[A-Z] => 시작 [^A-Z] => 제외
	// [A-Z]$ => 끝  [A-Z]
	// . => 임의의 한글자
	// *(0 이상) / +(1 이상) / ?(0,1)
	// 정규식
	// [] => 패턴 , {} => 갯수
	// [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}
	// 맵다 맵고 맵게 => 맵+
	// REGEXP_LIKE(column,정규식)
 	System.out.println(price);
}
}
