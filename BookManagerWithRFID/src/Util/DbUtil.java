package Util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类
 * @author JH
 *
 */
public class DbUtil {
	
	private static String dbUrl="jdbc:mysql://localhost:3306/rfid_book";               //数据库地址    
	private static String dbUserName="root";                                    //数据库用户名
	private static String dbPassword="123456";                                  //数据库密码
//	private static String jdbcName="com.jdbc.mysql.Drive";                      //驱动名字
	
	/**
	 * 数据库链接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");                                 //加载驱动
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//数据库链接 
        return con;
	}
	
	/**
	 * 数据库关闭
	 * @param pstmt
	 * @param con
	 * @throws Exception
	 */
	public void close(Connection con) throws Exception{  
			if(con!=null)
				con.close();
		}
	
}
