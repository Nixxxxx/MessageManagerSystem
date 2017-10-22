package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.BookType;

public class BookTypeDao {

	/**
	 * ͼ���������
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static int add(Connection con,BookType bookType) throws SQLException{;
		String sql="insert into booktype values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bookType.getBooktype());
		pstmt.setString(2,bookType.getBooktypedesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ͼ�����Ͳ�ѯ
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet search(Connection con,BookType bookType) throws SQLException{
		String sql="select * from booktype where booktypename like '%"+bookType.getBooktype()+"%'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public static ResultSet searchid(Connection con,BookType bookType) throws SQLException{
		String sql="select * from booktype where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bookType.getId()+"");
		return pstmt.executeQuery();
	}
	
	/**
	 * ͼ�������޸�
	 */
	public static  int update(Connection con,BookType bookType) throws SQLException{
		int result=isRepeat(con,bookType);
		if(result<0) return -1;
		String sql="update booktype set booktypename=?,booktypedesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bookType.getBooktype());
		pstmt.setString(2,bookType.getBooktypedesc());
		pstmt.setInt(3,bookType.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ���ͼ�����������Ƿ����
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public static int isRepeat(Connection con,BookType bookType) throws SQLException{
		String sql="select * from booktype where booktypename=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bookType.getBooktype());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			sql="select * from booktype where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,bookType.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("booktypename").equals(bookType.getBooktype()))
				return 1;
			}
			return -1;
		}
		return 0;
	}
	/**
	 * ����ͼ�������Ƿ�����
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException 
	 */
	
	public ResultSet hasBook(Connection con,BookType bookType) throws SQLException{
		String sql="select * from book where booktypeid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, bookType.getId());
		return pstmt.executeQuery();
	}
	
	/**
	 * ͼ������ɾ��
	 * @param con
	 * @param bookType
	 * @return
	 * @throws SQLException
	 */
	public int delete(Connection con,BookType bookType) throws SQLException{
		String sql="delete from booktype where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, bookType.getId());
		return pstmt.executeUpdate();
	}
}
