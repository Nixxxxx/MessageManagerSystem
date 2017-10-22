package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * ���ݿ⹤����
 * @author JH
 *
 */
public class DbUtil {
	
	private static String dbUrl="jdbc:mysql://localhost:3306/db_book";               //���ݿ��ַ    
	private static String dbUserName="root";                                    //���ݿ��û���
	private static String dbPassword="123456";                                  //���ݿ�����
	private static String jdbcName="com.jdbc.mysql.Drive";                      //��������
	
	/**
	 * ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public static Connection getCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");                                 //��������
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//���ݿ����� 
        return con;
	}
	
	/**
	 * ���ݿ�ر�
	 * @param pstmt
	 * @param con
	 * @throws Exception
	 */
	public void close(Connection con) throws Exception{  
			if(con!=null)
				con.close();
		}
	
}
