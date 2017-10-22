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
 * 工具类
 */

public class Util { 
	
	public static final int FRAME_WIDTH=450;
	public static final int FRAME_HEIGHT=600;
	public static final int LOCATION_WIDTH=750;
	public static final int LOCATION_HEIGHT=200;
	

	
	private static String dbUrl="jdbc:mysql://localhost:3306/db";               //数据库地址    
	private static String dbUserName="root";                                    //用户名
	private static String dbPassword="123456";                                  //密码
	private static String jdbcName="com.jdbc.mysql.Drive";                      //驱动名字
	
	
	public static Connection getCon() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");                                 //加载驱动
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);//数据库链接 
        return con;
	}
	
	public void close(PreparedStatement pstmt,Connection con) throws Exception{           //数据库关闭
		if(pstmt!=null){
			pstmt.close();
			if(con!=null)
				con.close();
		}
		
	}
		
	
	public static Image getImage(String path){                                   //返回图片
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
