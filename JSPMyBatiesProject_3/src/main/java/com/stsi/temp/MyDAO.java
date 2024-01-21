package com.stsi.temp;
import java.util.*;

import com.sist.vo.FoodVO;
import com.sist.vo.ReserveDayVO;

import java.sql.*;

public class MyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	public MyDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
		
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception e) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	
	public List<Integer> foodNumberData()
	{
		List<Integer> list=new ArrayList<Integer>();
		try
		{
			getConnection(); // session=ssf.opensesstion
			String sql="SELECT fno FROM food_menu_house "
					+ "ORDER BY fno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int fno=rs.getInt(1);
				list.add(fno);
			}
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection(); // session,close()
		}
		return list;
	}
	
	public void foodTimeInsert(ReserveDayVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO reserve_day VALUES("
					+ "?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getRno());
			ps.setString(2, vo.getRtime());
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	public void foodDayInsert(FoodVO vo) // 예약 가능한 날짜 
	{
		try
		{
			getConnection();
			String sql="UPDATE food_menu_house SET "
					+ "rday=? "
					+ "WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getRday());
			ps.setInt(2, vo.getFno());
			ps.executeUpdate();
			ps.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	 
	
}
