package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.dbcp.*;
import com.sist.vo.MemberVO;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	public static MemberDAO dao;
	
	// 1. 로그인 
	public MemberVO isLogin(String id,String pwd)
	{
		// Spring에서 변경 => 암호화/복호화 
		MemberVO vo=new MemberVO();
		try
		{
			conn=dbconn.getConnection();
			// id 존재 여부 확인 
			String sql="SELECT COUNT(*) FROM jspmember "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			// id가 있는 경우 pwd 확인
			if(count==0) // ID가 없는 상태
			{
				vo.setMsg("NOID");
			}
			else
			{
				// 세션에 저장할 데이터 
				sql="SELECT id, name, admin, pwd "
						+ "FROM jspmember "
						+ "WHERE id=?";
				// "WHERE id='"+id +"'" => 오류 발생 => 문자열은 '' 붙혀줘야됨
				// setstring 주게되면 자동으로 'id' 로 들어감
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_id=rs.getString(1);
				String name=rs.getString(2);
				String admin=rs.getString(3);
				String db_pwd=rs.getString(4);
				
				if(db_pwd.equals(pwd))
				{
					vo.setMsg("OK");// session에 저장
					vo.setId(db_id);
					vo.setName(name);
					vo.setAdmin(admin);
				}
				else
				{
					vo.setMsg("NOPWD");
				}
				rs.close();	
			}
			
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
