package com.sist.dao;
import java.io.*;
import java.util.*;
//musicFindData

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MusicDAO {
	private static SqlSessionFactory ssf;
	// 초기화 => XML을 파싱
	static 
	{
		try
		{
			//1. XML을 읽어 온다
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// 2. XML에 설정된 데이터 읽기 => MAP
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// mybaits에서 제공하는 태그와 속성만 사용해야한다
			// 제공하는 태그는 => dtd에 선언되어있음 (http://mybatis.org/dtd/mybatis-3-mapper.dtd)
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	public static List<MusicVO> musicFindData(Map map) // 체크박스 , 검색어 => String[] fs,String ss
	{
		SqlSession session=null;
		List<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("musicFindData",map);
			
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
		
		return list;
	}
}
