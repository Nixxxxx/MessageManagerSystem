package StudentMessageSystem;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.imageio.ImageIO;


/*
 * ������
 */

public class Util { 
	
	public static final int FRAME_WIDTH=450;
	public static final int FRAME_HEIGHT=600;
	public static final int LOCATION_WIDTH=750;
	public static final int LOCATION_HEIGHT=200;
	

	
	private static String dbUrl="jdbc:mysql://localhost:3306/db";               //���ݿ��ַ    
	private static String dbUserName="root";                                    //�û���
	private static String dbPassword="123456";                                  //����
	private static String jdbcName="com.jdbc.mysql.Drive";                      //��������
	
	
	public static Connection getCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");                                 //��������
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//���ݿ����� 
        return con;
	}
	
	public void close(PreparedStatement pstmt,Connection con) throws Exception{           //���ݿ�ر�
		if(pstmt!=null){
			pstmt.close();
			if(con!=null)
				con.close();
		}
		
	}
		
	
	public static Image getImage(String path){                                   //����ͼƬ
		URL u=Util.class.getClassLoader().getResource(path);
		BufferedImage img =null;
		try {
			img=ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
