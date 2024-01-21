package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class SeoulDAO {
	private static SqlSessionFactory ssf=CommonsDataBase.getSsf();
	
	// 기능설정
/*
 * <select id="seoulLocationListData" resultType="SeoulVO" parameterType="hashmap">
SELECT no,poster,title,num 
FROM(SELECT no,poster,title,ROWNUM AS num
FROM SELECT no,poster,title 
FROM seoul_location ORDER BY no ASC))
WHERE num BETWEEN #{start} AND #{end}
</select>
MyBatis => 태그 한개에 SQL문장을 한개만 수행한다
 */
	/*
	 * try
		{
			session=ssf.openSession();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	 */
	public static List<SeoulVO> seoulLocationListData(Map map)
	{
		SqlSession session=null;
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("seoulLocationListData",map);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static int seoulLocationTotalPage()
	{
		SqlSession session=null;
		int total=0;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("seoulLocationTotalPage");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	public static List<SeoulVO> seoulNatureListData(Map map)
	{
		SqlSession session=null;
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("seoulNatureListData",map);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static int seoulNatureTotalPage()
	{
		SqlSession session=null;
		int total=0;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("seoulNatureTotalPage");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	public static List<SeoulVO> seoulShopListData(Map map)
	{
		SqlSession session=null;
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("seoulShopListData",map);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static int seoulShopTotalPage()
	{
		SqlSession session=null;
		int total=0;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("seoulShopTotalPage");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	/*
	 * <update id="seoulLocationHitIncrement" parameterType="int">
 UPDATE seoul_location SET hit=hit+1
 WHERE no=#{no}
</update>
<select id="seoulLocationDetailData" resultType="SeoulVO" parameterType="int">
SELECT * FROM seoul_location
WHERE no=#{no}
</select>
	 */
	public static SeoulVO seoulLocationDetailData(int no)
	{
		// 매개변수는 사용자 요청값
		SeoulVO vo=new SeoulVO();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			session.update("seoulLocationHitIncrement",no);
			session.commit();
			vo=session.selectOne("seoulLocationDetailData",no);
			
		}catch(Exception ex)
		{
//			session.rollback();
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	/*<!-- 인접 맛집 -->
<select id="seoulFoodData" resultType="FoodVO" parameterType="String">
SELECT fno,name,poster,rownum
FROM food_menu_house
WHERE address LIKE '%'||#{address}||'%'
AND rownum&lt;=5
</select>
	
}
*/
	public static List<FoodVO> seoulFoodData(String address)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		SqlSession session=ssf.openSession();
		try
		{
			session=ssf.openSession();
			list=session.selectList("seoulFoodData", address);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
}