package com.sist.vo;
import java.util.*;
public class CartVO {
 private int cart_no,goods_no,type,amount,price,ischeck,issale;
 private String id;
 private MemberVO mvo=new MemberVO();
 private GoodsVO gvo=new GoodsVO();
 
 public MemberVO getMvo() {
	return mvo;
}
public void setMvo(MemberVO mvo) {
	this.mvo = mvo;
}
public GoodsVO getGvo() {
	return gvo;
}
public void setGvo(GoodsVO gvo) {
	this.gvo = gvo;
}
private Date regdate;
public int getCart_no() {
	return cart_no;
}
public void setCart_no(int cart_no) {
	this.cart_no = cart_no;
}
public int getGoods_no() {
	return goods_no;
}
public void setGoods_no(int goods_no) {
	this.goods_no = goods_no;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getIscheck() {
	return ischeck;
}
public void setIscheck(int ischeck) {
	this.ischeck = ischeck;
}
public int getIssale() {
	return issale;
}
public void setIssale(int issale) {
	this.issale = issale;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
 
}
