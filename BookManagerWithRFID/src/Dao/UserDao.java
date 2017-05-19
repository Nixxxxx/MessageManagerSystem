package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class UserDao {
	
	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User login(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from admin where username=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getUsername());
		pstmt.setString(2,user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUsername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
}
