package com.sist.dao;
import java.util.*;
import java.sql.*;

import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.*;
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static BoardDAO dao=new BoardDAO();
	private final int row_size=10;
	public static BoardDAO newInstance()
	{
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	
	public List<boardVO> boardListData(int page)
	{
		List<boardVO> list=new ArrayList<boardVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd'),hit,num "
					+ "FROM(SELECT no,subject,name,regdate,hit,ROWNUM AS num "
					+ "FROM(SELECT no,subject,name,regdate,hit "
					+ "FROM jspboard ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int start=(page*row_size)-(row_size-1);
			int end=page*row_size;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				boardVO vo=new boardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
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
	public int baordTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/"+row_size+")" 
					+"FROM jspboard";
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
	public void boardInsert(boardVO vo)
	{
		try
		{
			conn=dbconn.getConnection();
			String sql="INSERT INTO jspboard(no,name,subject,content,pwd) "
					+ "VALUES(jb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
	}
	public boardVO boardDetail(int no) 
	{
		boardVO vo=new boardVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE jspboard SET "
					+ "hit=hit+1 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),hit "
					+ "FROM jspboard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			
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
