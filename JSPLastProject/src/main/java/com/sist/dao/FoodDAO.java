package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.FoodVO;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static FoodDAO dao;
	
	public static FoodDAO newInstance() {
		
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	// 평점별 12개 출력
	public List<FoodVO> foodBestListData(String type)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,score,rownum "
					+ "FROM (SELECT fno,name,poster,score,type "
					+ "FROM food_menu_house ORDER BY score DESC) "
					+ "WHERE type=? AND rownum<=12 ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				list.add(vo);
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
	public List<FoodVO> foodFindData(int page,String addr)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,num "
					+ "FROM(SELECT fno,name,poster, ROWNUM AS num "
					+ "FROM food_menu_house WHERE address LIKE '%'||?||'%') " // 여러개 검색 시 OR문자로 이어줘야됨
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=20;
			int start=(page*rowsize)-(rowsize-1);
			int end=(page*rowsize);
			ps.setString(1, addr);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				list.add(vo);
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
	public int foodFindTotalPage(String addr)
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20) FROM food_menu_house "
					+ "WHERE REGEXP_LIKE(address,?)"; // address,'마포구|서대문구' => 여러개 검색 시 훨씬 편함
			ps=conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
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
		return total;
	}
	public FoodVO foodFindDetail(int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT poster, name, score, phone, address, type, theme, price, seat, time, content "
					+ "FROM food_menu_house WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setPoster("https://www.menupan.com"+rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setType(rs.getString(6));
			vo.setTheme(rs.getString(7));
			vo.setPrice(rs.getString(8));
			vo.setSeat(rs.getString(9));
			vo.setTime(rs.getString(10));
			vo.setContent(rs.getString(11));
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
	public List<FoodVO> foodListData(int page)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,name,poster,num "
					+ "FROM(SELECT fno,name,poster, ROWNUM AS num "
					+ "FROM food_menu_house ORDER BY fno ASC) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=20;
			int start=(page*rowsize)-(rowsize-1);
			int end=(page*rowsize);
//			ps.setString(1, addr);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				list.add(vo);
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
	public int foodTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20) FROM food_menu_house "; // address,'마포구|서대문구' => 여러개 검색 시 훨씬 편함
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
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
		return total;
	}
	// Top7 가지고옴
	public List<FoodVO> foodTop7()
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		 try
			{
				conn=dbconn.getConnection();
				String sql="SELECT fno,name,rownum "
						+ "FROM (SELECT fno,name "
						+ "FROM food_menu_house ORDER BY hit DESC) "
						+ "WHERE rownum<=7";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					FoodVO vo=new FoodVO();
					vo.setFno(rs.getInt(1));
					vo.setName(rs.getString(2));
					list.add(vo);
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
	public FoodVO foodDetail(int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE food_menu_house SET "
					+ "hit=hit+1 "
					+ "WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			
			sql="SELECT poster, name, score, phone, address, type, theme, price, seat, time, content "
					+ "FROM food_menu_house WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setPoster("https://www.menupan.com"+rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setType(rs.getString(6));
			vo.setTheme(rs.getString(7));
			vo.setPrice(rs.getString(8));
			vo.setSeat(rs.getString(9));
			vo.setTime(rs.getString(10));
			vo.setContent(rs.getString(11));
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

}
