package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

import oracle.jdbc.OracleTypes;
public class ReplyDAO {
	private Connection conn;
	private CallableStatement cs; // 함수호출시사용
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	private static ReplyDAO dao;
	public ReplyDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	//해제
	public void disConnection()
	{
		try
		{
			if(conn!=null)
				conn.close();
			if(cs!=null)
				cs.close();
		}catch(Exception ex) {}
	}
	// 싱글턴 
	public static ReplyDAO newInstance()
	{
		if(dao==null)
			dao=new ReplyDAO();
		return dao;
	}
	// 댓글 목록 읽기
	/*
	 * CREATE OR REPLACE PROCEDURE replyListData(
	 *  pno NUMBER,
    	pType NUMBER,
    	pResult OUT SYS_REFCURSOR // => 가지고 와야되는 값
	 */
	/*
	 *  no => 댓글 고유번호
	 *  type => 구분자(명소:1, 자연:2, 쇼핑:3, 맛집:4) 1
	 *  cno => 각 고유번호 => 5
	 *  
	 *  <select id="replyListData" resultType="ReplyVO" parameterMap="map"
	 *   statement="CALLABLE"
	 *  >
	 *  {CALL replyListData(?,?,?)}
	 *  </select>
	 *  SQLInjection => 가끔
	 */
	public List<ReplyVO> replyListData(int type, int cno)
	{
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try
		{
			getConnection();
			String sql="{CALL replyListData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, cno);
			cs.setInt(2, type);
			// 문자 , 숫자 , 커서 ==> OUT 변수일 경우에만 사용
			// OracleTypes.VARCHAR => Stirng
			// OracleTypes.INTEGER => int
			// OracleTypes.CURSOR => ResultSet
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			// 실행
			cs.executeQuery();
			// 레지스터에 저장된 값 읽기
			// 커서인 경우 
			ResultSet rs=(ResultSet)cs.getObject(3); // 값을 읽어옴
			// no,type,cno,id,name,msg,regdate
			while(rs.next())
			{
				ReplyVO vo=new ReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setType(rs.getInt(2));
				vo.setCno(rs.getInt(3));
				vo.setId(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setMsg(rs.getString(6));
				vo.setDbday(rs.getString(7));
				list.add(vo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	/*
	 * CREATE OR REPLACE PROCEDURE replyInsert(
    	pType reply_all.type%TYPE,
    	pCno reply_all.cno%TYPE,
    	pId reply_all.id%TYPE,
    	pName reply_all.name%TYPE,
    	pMsg reply_all.msg%TYPE
)
	 */
	public void replyInsert(ReplyVO vo)
	{
		try
		{
			getConnection();
			String sql="{CALL replyInsert(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getType());
			cs.setInt(2, vo.getCno());
			cs.setString(3, vo.getId());
			cs.setString(4, vo.getName());
			cs.setString(5, vo.getMsg());
			
			cs.executeQuery();
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
	/*
	 * -- 댓글 수정
CREATE OR REPLACE PROCEDURE replyUpdate(
    pNo reply_all.no%TYPE,
    pMsg reply_all.msg%TYPE
)
IS
BEGIN
    UPDATE reply_all SET
    msg=pMsg
    WHERE no=pNo;
    COMMIT;
END;
-- 댓글 삭제
CREATE OR REPLACE PROCEDURE replyDelete(
    pNo reply_all.no%TYPE
)
IS
BEGIN
    DELETE FROM reply_all
    WHERE no=pNo;
    COMMIT;
END;

	 */
	public void replyUpdate(int no,String msg)
	{
		try
		{
			getConnection();
			String sql="{CALL replyUpdate(?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, msg);
			cs.executeQuery();
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
	public void replyDelete(int no)
	{
		try
		{
			getConnection();
			String sql="{CALL replyDelete(?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.executeQuery();
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
}
