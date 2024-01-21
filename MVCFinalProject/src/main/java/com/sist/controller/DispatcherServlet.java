package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
 *   XML 파싱 
 *   Annotation => 클래스 찾기 => 메소드 찾기
 *   => MVC동작 
 *      1. 요청 (JSP) => <a> , <form>
 *         => DispatcherServlet(Controller) => 이미 제작 
 *         => @WebServlet("*.do")
 *                        ======= list.do , insert.do 
 *                                ====      ====== 구분문자
 *                  | 서버에서 받을 수 있는 부분 
 *                    URI,URL => 서버 연결은 주소란
 *                    => URI를 이용해서 => Model찾기 
 *      2. DispatcherServlet (Controller)
 *         역할 
 *         === 요청을 받는다 (브라우저 => 요청을 받는 파일
 *                                   => JSP / Servlet)
 *                        JSP ====> View(화면 출력)
 *                        Servlet ==> 연결(자바/HTML)
 *                                    ============= 
 *         === 분리된 Java를 찾는다 ===> 연결
 *                            request/session
 *      3. MVC목적 
 *         1) 보안 (JSP => 배포 (소스를 통으로 전송)) => 유지보수 
 *         2) 여러명이 동시 개발 
 *            Front - Back (자바/HTML)
 *         3) JSP의 단점 : 확장성 , 재사용 , 변경
 *            ========= 사이트 제작시 버린다 
 *         4) 신규사원 확장 
 *         => MVVM / MVVP
 *      4. 소프트웨어 => 회귀 
 *      ================== 
 *      
 *      5. 동작        
 *                    request
 *      JSP (링크,버튼) ======= DispatcherServlet
 *                                 ==> Model(DAO와 연동)
 *                                     ======
 *                                     request에 결과값 담기
 *                                     =>setAttribute()
 *                DispatcherServlet<==
 *                  => request 필요하다 => request를 JSP로 전송
 *                        JSP로 request를 전송 메소드
 *                        => forward(request,response)
 *                        => SELECT 
 *                  => request 필요없다 => 화면만 이동
 *                        화면만 변경하는 메소드 
 *                        => sendRedirect(파일명)
 *                        => 회원가입 , 로그인 , 글쓰기...
 *                        => INSERT/UPDATE/DELETE
 *        => DispatcherServlet은 수정 => 고정한다 => .jar
 *        
 *        1. 설정 파일 
 *           Spring : application-context.xml
 *                    application-datasource.xml
 *                    application-security.xml
 *                    => 태그 속성은 Spring에서 제공하는 것만 사용이 가능 
 *                    => dtd
 *     
 */
import java.util.*;
import java.net.*;
@WebServlet("*.do")
// => 
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> clsList=
    		     new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//1. XML의 경로 읽기 => XML안에 클래스 등록 
		try
		{
			URL url=this.getClass().getClassLoader().getResource(".");
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator);
			// window => \\ , Mac => /
			path=path.substring(0,path.lastIndexOf(File.separator));
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCFinalProject\WEB-INF
			System.out.println(path);
			path=path+File.separator+"application.xml";
			
			// XML파싱 => xml안에서 필요한 데이터를 추출 
			// 1. 파서기 생성 
			DocumentBuilderFactory dbf=
					DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			// 2. XML을 전송 
			Document doc=db.parse(new File(path));
			// 3. root태그 읽기 => 테이블
			Element root=doc.getDocumentElement();
			// beans ==> FROM 테이블명
			// 4. root밑에 있는 태그를 모아서 데이터 추출 
			NodeList list=root.getElementsByTagName("bean");
			// 1,2,3,4,5,6,7,8,9,10
		    // list=><bean> 
			for(int i=0;i<list.getLength();i++)
			{
				// <bean id="board" class="com.sist.model.BoardModel"/>
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println(id+":"+cls);
				
				clsList.add(cls);
			}
		}catch(Exception ex) {}
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI();
		System.out.println("사용자 요청 URI:"+uri);
		// http://localhost/JSPMVCFinalProject/board/list.do
		// /JSPMVCFinalProject/board/list.do
		// /JSPMVCFinalProject + 
		// list.do
		uri=uri.substring(request.getContextPath().length()+1);
		try
		{
			// RequestMapping
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				// 클래스 정보 읽기 
				/*
				 *   메소드 / 멤버변수 / 생성자 
				 */
				Object obj=clsName.
						getDeclaredConstructor().newInstance();
				// 클래스 정보를 이용해서 메모리 할당 
				// => 리플렉션 
				Method[] methods=clsName.getDeclaredMethods();
				//               클래스에 선언된 모든 메소드를 가지고 온다 
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					// 메소드 위에 선언된 @RequestMapping을 가지고 온다 
					// => m(메소드) => 위에 저장되어 있는 어노테이션을 가지고 온다 
					// 저장된 객체 
					/*
					 *   A a=new A() => a
					 *   A a=a.getClass()
					 *   A a=A.class
					 *   
					 *   public void aaa(int a,int b)
					 *   
					 *   aaa(10)
					 */
					if(rm.value().equals(uri))
					{
						// 
						
						String jsp=(String)m.invoke(obj, request,response);
						System.out.println("출력할 JSP:"+jsp);
						// 파일업로드 / 쿠키 
						//  m이란 메소드 호출한다 => invoke(객체,매개변수....)
						if(jsp==null) // void => ajax
						{
							return;
						}
						else if(jsp.startsWith("redirect:"))
						{
							// return "redirect:list.do"
							jsp=jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
						}
						else
						{
							RequestDispatcher rd=
									request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
					
				}
			}
		}catch(Exception ex) {
			   System.out.println("Error발생:"+ex.getMessage());
			ex.printStackTrace();
			
		}
		
	}

}