package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
import com.sist.vo.SeoulVO;

public class SeoulDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static SeoulDAO dao;
	public static SeoulDAO newInstance()
	{
		if(dao==null)
			dao=new SeoulDAO();
		return dao;
	}
	int Row_size=12;
	// 1. 기능 : 명소 출력 => seoul_location
	// 자연 => seoul_nature
	// 쇼핑 => seoul_shop
	public List<SeoulVO> seoulLocationListData(int page,String tab)
	{
		List<SeoulVO> list=new ArrayList<SeoulVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT no,title,poster,num "
					+ "FROM(SELECT no,title,poster,ROWNUM AS num "
					+ "FROM(SELECT no,title,poster "
					+ "FROM "+tab+" ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int start=(page*Row_size)-(Row_size-1);
			int end=(page*Row_size);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SeoulVO vo=new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	public int totalPage(String tab)
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/"+Row_size+") "
					+"FROM "+tab+"";
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
}
