package com.sist.temp;

import java.lang.reflect.Method;

class A{
	public void aaa()
	{
		System.out.println("A:aaa() Call");
	}
	public void bbb()
	{
		System.out.println("B:bbb() Call");
	}
	public void ccc()
	{
		System.out.println("C:ccc() Call");
	}
}
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*A a=new A();
		a.aaa();
		a.bbb();
		a.ccc();*/
		// 자바 코딩이 가능 할 때 사용
		try
		{
			// A a=new A();
			Class clsName=Class.forName("com.sist.temp.A");
			Object obj=clsName.getDeclaredConstructor().newInstance();
			/*A a=(A)obj;
			a.aaa();
			a.bbb();
			a.ccc();*/
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
				m.invoke(obj, null);
			}
			
		} catch(Exception ex) {}
	}

}
