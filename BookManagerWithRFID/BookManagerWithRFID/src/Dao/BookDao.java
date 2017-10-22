package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Model.Book;

public class BookDao {


	/**
	 * 图书添加
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static int add(Connection con,Book book) throws SQLException{;
		String sql="insert into book values(?,?,?,?,?,?,?,?,?,null,null)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,book.getId());
		pstmt.setString(2,book.getBookName());
		pstmt.setString(3,book.getAuthor());
		pstmt.setString(4,book.getSex());
		pstmt.setString(5,book.getPress());
		pstmt.setFloat(6,book.getPrice());
		pstmt.setString(7,book.getBookDesc());
		pstmt.setInt(8,book.getBookTypeId());
		pstmt.setInt(9,1);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书查询(编号id)
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet searchWithId(Connection con,Book book) throws SQLException{
		String sql="select * from book where id like '%"+book.getId()+"%' ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	
	
	/**
	 * 图书查询(其他信息)
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
		pstmt.setString(8,book.getId());
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
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 检查图书状态
	 * @return
	 * @throws SQLException 
	 */
	public int bookConditions(Connection con,Book book) throws SQLException{
		String sql="select * from book where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,book.getId());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("conditions");
		}else return -1;
	}
	
	/**
	 * 图书编号和学生编号匹配
	 * @param con
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public int bookBorrower(Connection con,Book book) throws SQLException{
		String sql="select * from book where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,book.getId());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			if(rs.getString("borrower").equals(book.getBorrower()))
			return 0;
		}
		return -1;
	}
	/**
	 * 图书借阅
	 * @param con
	 * @param book
	 * @throws SQLException
	 */
	public int bookBorrow(Connection con,Book book) throws SQLException{
		String sql="update book set conditions=?,outtime=?,borrower=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,0);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String dateString= sdf.format(book.getDate());
        pstmt.setString(2,dateString);
		pstmt.setString(3,book.getBorrower());
		pstmt.setString(4,book.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书归还
	 * @throws SQLException 
	 */
	public int bookReturn(Connection con,Book book) throws SQLException{
		String sql="update book set conditions=?,outtime=?,borrower=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,1);
		pstmt.setString(2,null);
		pstmt.setString(3,null);
		pstmt.setString(4,book.getId());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 图书借阅记录（读取借出图书）
	 * @param con
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public ResultSet bookRecord(Connection con) throws SQLException{
		String sql="select * from book where conditions=0 order by outtime asc";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
}