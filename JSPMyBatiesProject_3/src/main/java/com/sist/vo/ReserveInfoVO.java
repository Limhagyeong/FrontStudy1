package com.sist.vo;
import java.util.*;
public class ReserveInfoVO {
	private int no, fno, ok;
	private String id, day, time, inwon;
	private FoodVO fvo=new FoodVO();
	private Date regdate;
	
	

	public FoodVO getFvo() {
		return fvo;
	}
	public void setFvo(FoodVO fvo) {
		this.fvo = fvo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getOk() {
		return ok;
	}
	public void setOk(int ok) {
		this.ok = ok;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInwon() {
		return inwon;
	}
	public void setInwon(String inwon) {
		this.inwon = inwon;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fno+" "+day+" "+time+" "+inwon+" "+id; // 주소출력 대신 값 출력 => 값 정상 받는지 확인
	}
	
	
}
