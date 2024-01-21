package com.sist.dao;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.*;
public class BoardDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static BoardDAO dao;
   // 미리 연결된 Connection 객체 얻기 
   public void getConnection()
   {
	  try
	  {
		  Context init=new InitialContext();
		  Context c=(Context)init.lookup("java://comp/env");
		  DataSource ds=(DataSource)c.lookup("jdbc/oracle");
		  conn=ds.getConnection();
	  }catch(Exception ex) {}
   }
   // 반환 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   public static BoardDAO newInstance()
   {
	   if(dao==null)
		   dao=new BoardDAO();
	   return dao;
   }
   
   // 기능 => 목록 (페이지)
   // => 화면에 출력할 데이터 (리턴형)     사용자 요청한 값 (매개변수)
   public List<BoardVO> boardListData(int page)
   {
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT no,subject,name,"
		   		     +"TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
				     +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
		   		     +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
				     +"FROM replyBoard ORDER BY group_id DESC,group_step ASC)) "
		   		     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=10;
		   int start=(page*rowSize)-(rowSize-1);
		   int end=rowSize*page;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   BoardVO vo=new BoardVO();
			   vo.setNo(rs.getInt(1));
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setDbday(rs.getString(4));
			   vo.setHit(rs.getInt(5));
			   vo.setGroup_tab(rs.getInt(6));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   // findByNo(int no) where no=1
   public int boardRowCount()
   {
	   int count=0;
	   try
	   {
		   getConnection();
		   String sql="SELECT COUNT(*) FROM replyBoard";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   count=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return count;
   }
   public void boardInsert(BoardVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO replyBoard("
				     +"no,name,subject,content,pwd,group_id) "
				     +"VALUES(rb_no_seq.nextval,?,?,?,?,"
				     +"(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getName());
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getContent());
		   ps.setString(4, vo.getPwd());
		   ps.executeUpdate();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   // 상세보기 => 기능처리
   public BoardVO boardDetailData(int no)
   {
	   BoardVO vo=new BoardVO();
	   try
	   {
		   getConnection();
		   //Spring 
		   String sql="UPDATE replyboard SET "
		   		+ "hit=hit+1 "
		   		+ "WHERE no="+no;
		   ps=conn.prepareStatement(sql);
		   ps.executeUpdate();
		   // 조회수 증가
		   sql="SELECT no,name,subject,content,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi:ss'),hit "
		   		+ "FROM replyboard "
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
		   disConnection();
	   }
	   return vo;
   }
   
   // 수정 데이터 읽기
   public BoardVO boardUpdateData(int no)
   {
	   BoardVO vo=new BoardVO();
	   try
	   {
		   getConnection();
		   // 조회수 증가
		   String sql="SELECT no,name,subject,content "
		   		+ "FROM replyboard "
		   		+ "WHERE no="+no;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setNo(rs.getInt(1));
		   vo.setName(rs.getString(2));
		   vo.setSubject(rs.getString(3));
		   vo.setContent(rs.getString(4));
		   rs.close();
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return vo;
   }
   
   // 실제 수정 => request, response => Ajax (70%)
   public boolean boardUpdate(BoardVO vo)
   {
	   boolean bCheck=false;
	   try
	   {
		   getConnection();
		   String sql="SELECT pwd FROM replyboard "
		   		+ "WHERE no="+vo.getNo();
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   String db_pwd=rs.getString(1);
		   rs.close();
		   
		   if(db_pwd.equals(vo.getPwd()))
		   {
			   // 수정
			   bCheck=true;
			   sql="UPDATE replyBoard SET "
			   		+ "name=?,subject=?,content=? "
			   		+ "WHERE no=?";
			   
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setInt(4, vo.getNo());
			   
			   //실행
			   ps.executeUpdate();
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
	   return bCheck;
   }
   // 답변
   public void boardReplyInsert(int pno,BoardVO vo)
   {
	   // 1. pno => group_id, step, tab
	   
	   // 2. => 답변형의 핵심!!!
	   /*						gi  gs  gt => 답변, 대답변 구분을 위한 공백
	    *  AAAAAA				 1   0   0 
	    *    =>BBBBB			 1   1   1
	    *    =>KKKKK             1   1   1 => 2. 답변 섞임 
	    *     =>CCCCC            1   2   2
	    *    =>KKKKK             1   1   1 => 1. 기존에 새로 추가되는 개념 / ASC => 해결
	    *  DDDDDD 				 2   0   0
	    *  
	    *  2. 해결
	    *   최신 답변을 가장 위로 보내줌
	    *   
	    * 
	    */
	   // 3. insert
	   // 4. depth 증가
	   try
	   {
		   getConnection();
		   //1. gi,gs,gt
		   String sql="SELECT group_id,group_step,group_tab "
		   		+ "FROM replyboard "
		   		+ "WHERE no="+pno;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   int group_id=rs.getInt(1);
		   int group_step=rs.getInt(2);
		   int group_tab=rs.getInt(3);
		   rs.close();
		   
		   // 출력 위치 조정 (댓글, 답변 순서) // group_step=0 (default)
		   sql="UPDATE replyBoard SET "
		   		+ "group_step=group_step+1 "
		   		+ "WHERE group_id=? AND group_step>?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, group_id);
		   ps.setInt(2, group_step);
		   ps.executeUpdate();
		   
		   // 실제 답변 저장
		   sql="INSERT INTO replyboard(no,name,subject,content,pwd,"
		   		+ "group_id, group_step, group_tab,root) "
		   		+ "VALUES(rb_no_seq.nextval,?,?,?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getName());
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getContent());
		   ps.setString(4, vo.getPwd());
		   ps.setInt(5, group_id);
		   ps.setInt(6, group_step+1);
		   ps.setInt(7, group_tab+1);
		   ps.setInt(8, pno);
		   ps.executeUpdate();
		   
		   sql="UPDATE replyboard SET "
		   		+ "depth=depth+1 "
		   		+ "WHERE no="+pno;
		   ps=conn.prepareStatement(sql);
		   ps.executeUpdate();
		   // depth 증가
		   
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
   // 삭제 => 90%
   public boolean boardDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   try
	   {
		   getConnection();
		   //1. 비밀번호 검색
		   /*
		    *  2. 
		    *  	비밀번호(O)
		    *   2-1. root, depth
		    *        => depth==0 (답변 X) ==> delete
		    *           depth!=0 (답변 O) ==> update => 제목 : 관리자가 삭제한 게시물입니다
		    *   2-2. depth를 감소 => root 
		    *   비밀번호(X) => 종료
		    */
		   String sql="SELECT pwd,root,depth "
		   		+ "FROM replyboard "
		   		+ "WHERE no="+no;
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   String db_pwd=rs.getString(1);
		   int root=rs.getInt(2);
		   int depth=rs.getInt(3);
		   rs.close();
		   if(db_pwd.equals(pwd))
		   {
			   bCheck=true;
			   if(depth==0) // 답변이 없다면 
			   {
				   sql="DELETE FROM replyboard "
				   		+ "WHERE no="+no;
				   ps=conn.prepareStatement(sql);
				   ps.executeUpdate();
			   }
			   else // 답변이 있다면 
			   {
				   String msg="관리자가 삭제한 게시글입니다";
				   sql="UPDATE replyboard SET "
				   		+ "subject=?,content=? "
				   		+ "WHERE no=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, msg);
				   ps.setString(2, msg);
				   ps.setInt(3, no);
				   ps.executeUpdate();
			   }
			   sql="UPDATE replyboard SET "
			   		+ "depth=depth-1 "
			   		+ "WHERE no="+root; // 답변이 달린 게시글(root)의 답변(depth)를 감소시킨다
			   ps=conn.prepareStatement(sql);
			   ps.executeUpdate();
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
	   return bCheck;
   }
   
}