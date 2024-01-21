package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dbcp.*;
public class AllReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static AllReplyDAO dao;
	public static AllReplyDAO newInstance()
	{
		if(dao==null)
			dao=new AllReplyDAO();
		return dao;
	}
}
