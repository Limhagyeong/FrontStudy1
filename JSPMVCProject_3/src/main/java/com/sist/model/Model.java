package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	public String handleRequest(HttpServletRequest request); // handle 스프링 인터페이스 명칭
}
