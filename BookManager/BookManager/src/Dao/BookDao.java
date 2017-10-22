package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Book;
import Model.BookType;
import Util.StringUtil;

public class BookDao {


	/**
	 * 图书添加
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static int add(Connection con,Book book) throws SQLException{;
		String sql="insert into book values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,book.getBookName());
		pstmt.setString(2,book.getAuthor());
		pstmt.setString(3,book.getSex());
		pstmt.setString(4,book.getPress());
		pstmt.setFloat(5,book.getPrice());
		pstmt.setString(6,book.getBookDesc());
		pstmt.setInt(7,book.getBookTypeId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书查询
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet search(Connection con,Book book) throws SQLException{
		StringBuffer sql=new StringBuffer("select * from book where bookname like '%"+book.getBookName()+"%' "
				+ "and author like '%"+book.getAuthor()+"%'");
		if(book.getBookTypeId()!=0)
		sql.append(" and  booktypeid="+book.getBookTypeId());
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 图书修改
	 */
	public static  int update(Connection con,Book book) throws SQLException{
		String sql="update book set bookname=?,author=?,sex=?,press=?,price=?,bookTypeId=?,bookdesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,book.getBookName());
		pstmt.setString(2,book.getAuthor());
		pstmt.setString(3,book.getSex());
		pstmt.setString(4,book.getPress());
		pstmt.setFloat(5,book.getPrice());
		pstmt.setInt(6,book.getBookTypeId());
		pstmt.setString(7,book.getBookDesc());
		pstmt.setInt(8,book.getId());
		return pstmt.executeUpdate();
	}

	
	/**
	 * 图书类型删除
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static int delete(Connection con,String id) throws SQLException{
		String sql="delete from book where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,Integer.parseInt(id));
		return pstmt.executeUpdate();
	}
}
