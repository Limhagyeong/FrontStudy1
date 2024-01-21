package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class GoodsDAO {
/*
 * <select id="goodsListData" resultType="GoodsVO" parameterType="hashmap">
 SELECT no,goods_poster,goods_name,goods_price,num
 FROM(SELECT no,goods_poster,goods_name,goods_price,ROWNUM AS num 
 FROM(SELECT no,goods_poster,goods_name,goods_price 
 FROM ${tab_name} ORDER BY no ASC))
 WHERE num BETWEEN #{start} AND #{end}
</select>
 */
	private static SqlSessionFactory ssf=CommonsDataBase.getSsf();
	/*
	 * 	public List<BoardVO> boardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		try
		{
			conn=dbconn.getConnection();
			ssf.openSession()
			String sql="SELECT no, subject, name, TO_CHAR(regdate,'yyyy-mm-dd'),hit, num  "
					+ "FROM(SELECT no, subject, name, regdate,hit, ROWNUM AS num "
					+ "FROM (SELECT + INDEX_DESC(project_board pb_no_pk)+no, subject, name, regdate,hit "
					+ "FROM project_board)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowsize=10;
			int start=(page*rowsize)-(rowsize-1);
			int end=(page*rowsize);
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
	 */
	
	// 기능설정
	public static List<GoodsVO> goodsListData(Map map)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("goodsListData",map);
			
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
		return list;
	}
	/*
	 * <select id="goodsTotalPage" resultType="int" parameterType="hashmap">
 SELECT CEIL(COUNT(*)/20.0) FROM ${tab_name} 
</select>
	 */
	public static int goodsTotalPage(Map map)
	{
		int total=0;
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			total=session.selectOne("goodsTotalPage",map);
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
		return total;
	}
	/*
	 * <select id="goodsDetailData" resultType="goodsVO" parameterType="hashmap">
SELECT * FROM #{tab_name}
WHERE no=#{no}
</select>

자동 구현 
=======
return형 = resultType
매개변수 = ParameterType
메소드명 = id

1. xml
2. annotation : Spring 
3. 실무 = XML + annotation 
	 */
	public static GoodsVO goodsDetailData(Map map)
	{
		GoodsVO vo=new GoodsVO();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			vo=session.selectOne("goodsDetailData",map);
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
	/*
	 * <select id="cartIsGoodsCount" resultType="int" parameterType="CartVO">
SELECT COUNT(*) FROM cart WHERE goods_no=#{goods_no} AND type=#{type} AND issale!=1
AND id=#{id}
</select>

<update id="cartGoodsUpdate" parameterType="CartVO">
UPDATE cart SET amount=amount+#{amount} 
WHERE goods_no=#{goods_no} AND type=#{type} AND id=#{id}
</update>

<insert id="cartGoodsInsert" parameterType="CartVO">
INSERT INTO cart VALUES(
	(SELECT NVL(MAX(cart_no)+1,1) FROM cart),
	#{goods_no},#{type},#{amount},#{price},#{id},0,0,SYSDATE
)
</insert>
	 */
	public static void cartInsert(CartVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			int count=session.selectOne("cartIsGoodsCount",vo);
			// count => 구매가 안된 상품이 있는지 확인
			if(count!=0) // 존재 => 수량만 증가 (기존 장바구니에 담겨있는 상품에 수량만 증가)
			{
				session.update("cartGoodsUpdate",vo);
			}
			else // 없다 => 장바구니에 데이터 추가
			{
				session.insert("cartGoodsInsert",vo);
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
	}
	/*
	 * <select id="mypageGoodsCartData" resultMap="cartMap" parameterType="hashmap">
SELECT cart_no,goods_no,amount,regdate,issale,ischeck,price,
(SELECT goods_poster FROM ${tab_name} WHERE no=cart.goods_no) as goods_poster,
(SELECT goods_name FROM ${tab_name} WHERE no=cart.goods_no) as goods_name,
(SELECT goods_price FROM ${tab_name} WHERE no=cart.goods_no) as goods_price
FROM cart
WHERE id=#{id} AND issale!=1
ORDER BY cart_no DESC
</select>
	 */
	public static List<CartVO> mypageGoodsCartData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mypageGoodsCartData",map);
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
		return list;
	}
	public static void cartBuyInsert(CartVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.insert("cartBuyInsert",vo);
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
	}//mypageCartBuyData
	public static List<CartVO> mypageCartBuyData(Map map)
	{
		List<CartVO> list=new ArrayList<CartVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mypageCartBuyData",map);
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
		return list;
	}
	//cartDelete
	public static void cartDelete(int no)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.delete("cartDelete",no);
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
}
	public static void goodsCartBuy(int cart_no)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.update("goodsCartBuy",cart_no);
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
	}
}
