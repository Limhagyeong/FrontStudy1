package com.sist.dao;
import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDAO {
	private static SqlSessionFactory ssf=CommonsDataBase.getSsf();
	// 기능설정
	public static  MemberVO isLogin(String id,String pwd)
	{
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			int count=session.selectOne("memberIdCount",id);
		 // ===> resultType과 일치                        #{id}
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				MemberVO dvo=session.selectOne("memberPwd",id);
				if(pwd.equals(dvo.getPwd()))
				{
					vo.setMsg("OK");
					vo.setId(dvo.getId());
					vo.setName(dvo.getName());
					vo.setAdmin(dvo.getAdmin());
					vo.setPhone(dvo.getPhone());
					vo.setAddr1(dvo.getAddr1());
					vo.setAddr2(dvo.getAddr2());
					vo.setPost(dvo.getPost());
					vo.setEmail(dvo.getEmail());
				}
				else
				{
					vo.setMsg("NOPWD");
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
}

