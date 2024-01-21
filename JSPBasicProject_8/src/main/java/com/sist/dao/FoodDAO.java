package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.*;
public class FoodDAO {
 // 1. 연결 객체
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	public static FoodDAO dao;
	
//	public static FoodDAO newInstacne()
//	{
//		if(dao==null)
//			dao=new FoodDAO();
//		return dao;
//	}
	
	// 기능 => 1. 카테고리 읽기
	public List<FoodCategoryVO> food_category_data()
	{
		List<FoodCategoryVO> list=new ArrayList<FoodCategoryVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT cno,title,poster "
					+ "FROM food_category "
					+ "ORDER BY cno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodCategoryVO vo=new FoodCategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
				//ROW => 매칭 (vo,bean)
			}
			rs.close();
					
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	// 기능 => 2. 로그인 => session(***)
	// => 메뉴 변경 => session
	// 2. 전체 데이터 읽기
	public List<FoodVO> foodAllData(int page)
	{
		List<FoodVO> vo=new ArrayList<FoodVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,num "
					+ "FROM(SELECT fno,name,poster,ROWNUM AS num "
					+ "FROM(SELECT fno,name,poster "
					+ "FROM food_menu_house ORDER BY fno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(page*rowSize)-(rowSize-1);
			int end=(page*rowSize);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo1=new FoodVO();
				vo1.setFno(rs.getInt(1));
				vo1.setName(rs.getString(2));
				vo1.setPoster(rs.getString(3));
				vo.add(vo1);
			}
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		
		return vo;
	}
	// 3. 전체 데이처 총 페이지 읽기
	 
	   public int foodAlltotalpage() {
	      int total=0;
	      
	      try {
	         conn=dbconn.getConnection();
	         String sql="SELECT CEIL(COUNT(*)/12.0) "
	                  +"FROM food_menu_house";
	         ps=conn.prepareStatement(sql);
	         ResultSet rs =ps.executeQuery();
	         rs.next();
	         total=rs.getInt(1);
	         rs.close();
	   } catch (Exception e) {
	      // TODO: handle exception
	      e.printStackTrace();
	   }
	      finally {
	      dbconn.disConnection(conn, ps);
	   }
	      
	      return total;
	      
	   }
	// 4. 검색데이터 읽기
	   public List<FoodVO> foodfindData(int page,String fs,String ss)
		{
			List<FoodVO> vo=new ArrayList<FoodVO>();
			try
			{
				conn=dbconn.getConnection();
				String sql="SELECT fno,name,poster,num "
						+ "FROM(SELECT fno,name,poster,ROWNUM AS num "
						+ "FROM(SELECT fno,name,poster "
						+ "FROM food_menu_house "
						+ "WHERE "+fs+ " LIKE '%'||?||'%' "
						+ "ORDER BY fno ASC)) "
						+ "WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, ss);
				int rowSize=12;
				int start=(page*rowSize)-(rowSize-1);
				int end=(page*rowSize);
				ps.setInt(2, start);
				ps.setInt(3, end);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					FoodVO vo1=new FoodVO();
					vo1.setFno(rs.getInt(1));
					vo1.setName(rs.getString(2));
					vo1.setPoster(rs.getString(3));
					vo.add(vo1);
				}
				rs.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				dbconn.disConnection(conn, ps);
			}
			
			return vo;
		}
	// 5. 검색 데이터 퐁 페이지 읽기
	   public int foodFindtotalpage(String fs,String ss) {
		      int total=0;
		      
		      try {
		         conn=dbconn.getConnection();
		         String sql="SELECT CEIL(COUNT(*)/12.0) "
		                  +"FROM food_menu_house "
		                  + "WHERE "+fs+" LIKE '%'||?||'%'";
		         ps=conn.prepareStatement(sql);
		         ps.setString(1, ss);
		         ResultSet rs =ps.executeQuery();
		         rs.next();
		         total=rs.getInt(1);
		         rs.close();
		   } catch (Exception e) {
		      // TODO: handle exception
		      e.printStackTrace();
		   }
		      finally {
		      dbconn.disConnection(conn, ps);
		   }
		      
		      return total;
		      
		   }
	
	
}
