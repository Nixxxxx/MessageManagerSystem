package StudentMessageSystem;

import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Methods {
	private static Util db=new Util();
	
	public void setFrame(MyFrame frame,String title,int close){                                            //窗口设置
		BackgroundPanel bgp;                                                        //背景图片
	    bgp=new BackgroundPanel((new ImageIcon("images/bg.jpg")).getImage());  
	    bgp.setBounds(0,0,450,600);  
	    frame.getContentPane().add(bgp);
		
		
		frame.setTitle(title);                                    //窗口标题
		frame.setSize(Util.FRAME_WIDTH,Util.FRAME_HEIGHT);        //窗口大小
		frame.setLocationRelativeTo(null);                        //窗口居中
		frame.setResizable(false);                                //窗口无法拉伸、最大化
		if(close==1)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //点击窗口右上角“X”，程序结束运行
		frame.setVisible(true);                                   //窗口显示
	}
	
//	public void paintComponent(Graphics g ){
//		int x=0,y=0;
//		ImageIcon icon=new ImageIcon("images/bg.jpg");
//		g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(),
//				icon.getIconHeight(), icon.getImageObserver());
////		g.drawImage(icon.getImage(),0,0,frame.getSize().width,frame.getSize().height,this);
//	}
	
	
	public void addStudent(MyFrame frame,String id,String name,String sex,String age0,String chinese0,String math0,String english0){        //添加学生信息
		int result=1;
		char[] age1=age0.toCharArray();
		char[] chinese1=chinese0.toCharArray();
		char[] math1=math0.toCharArray();
		char[] english1=english0.toCharArray();
		for(int i=0;i<age1.length;i++){
			if(age1[i]<48||age1[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		for(int i=0;i<chinese1.length;i++){
			if(chinese1[i]<48||chinese1[i]>57) {result=0;break;}
			if(i>2) {
				result=0;break;
			}
		}
		for(int i=0;i<math1.length;i++){
			if(math1[i]<48||math1[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		for(int i=0;i<english1.length;i++){
			if(english1[i]<48||english1[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		
		
			if(id.isEmpty()||name.isEmpty())JOptionPane.showMessageDialog(null, "学号、姓名不能为空！");
			else{
				if(result==1){
					int age=0,chinese=0,math=0,english=0;
					if(!age0.equals(""))age=Integer.parseInt(age0);
					if(!chinese0.equals(""))chinese=Integer.parseInt(chinese0);
					if(!math0.equals(""))math=Integer.parseInt(math0);
					if(!english0.equals(""))english=Integer.parseInt(english0);
					try {
						Connection con=db.getCon();
						String sql="select id from db_student";
						PreparedStatement pstmt=con.prepareStatement(sql);
						ResultSet rs=pstmt.executeQuery();
						while(rs.next()){
							if(rs.getString(1).equals(id)) {
								result=0;break;
							}
						}
						if(result==1){
							sql="insert into db_student values(?,?,?,?,?,?,?,?,?)";
							pstmt=con.prepareStatement(sql);
							pstmt.setString(1,id);
							pstmt.setString(2,name);
							pstmt.setString(3,sex);
							pstmt.setInt(4,age);
							pstmt.setInt(5,chinese);
							pstmt.setInt(6,math);
							pstmt.setInt(7,english);
							pstmt.setInt(8, chinese+math+english);
							pstmt.setInt(9,0);
							result=pstmt.executeUpdate();
					}
					else JOptionPane.showMessageDialog(null, "学号已存在！");
					if(result==1){
						JOptionPane.showMessageDialog(null,"添加成功！");
						new Methods().updateStudent(id,1,1);
						frame.dispose();
						new MyFrame().myFrame02();
						}
					else JOptionPane.showMessageDialog(null,"添加失败！");
					db.close(pstmt, con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
				else JOptionPane.showMessageDialog(null,"输入数据有误");
		}
	}
	
	public void updateStudent(String id,int model,int rank) throws Exception{                        //rank成绩排名刷新(1:添加后刷新，2：修改后刷新3：删除后刷新
		if(model==3||model==2){
			Connection con=db.getCon();
			String sql="select score,rank,id from db_student";
			PreparedStatement pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			rs.last();
			int length=rs.getRow();                 //获取数据库数据条数
			rs.beforeFirst();
			while(rs.next()){
					if(rank==length) break;
					sql="update db_student set rank=? where rank=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,rank);
					pstmt.setInt(2,rank+1);
					pstmt.executeUpdate();
					rs.beforeFirst();
					rank++;
					}
		}
		
		
		if(model==1||model==2){
			rank=1;
			Connection con=db.getCon();
			String sql="select score,rank,id from db_student where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			ResultSet rrs=pstmt.executeQuery();
			sql="select score,rank,id from db_student order by rank asc";
			pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			rs.last();
			int length=rs.getRow();                 //获取数据库数据条数
			rs.beforeFirst();
			if(rs.next()){
				rs.previous();
				while(rs.next()&&rrs.next()){
					if(rrs.getInt("score")<rs.getInt("score")) rank=rs.getInt("rank")+1;
					else if(rrs.getInt("score")==rs.getInt("score")&&Integer.parseInt(rrs.getString("id"))>Integer.parseInt(rs.getString("id")))
						{rank=rs.getInt("rank")+1;}
					rrs.previous();
				}
				System.out.println(rank);
				rs.beforeFirst();
			while(rs.next()){
					if(rank==length) break;
					sql="update db_student set rank=? where rank=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,length);
					pstmt.setInt(2,length-1);
					pstmt.executeUpdate();
					rs.beforeFirst();
					length--;
					}
				}
				sql="update db_student set rank=? where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,rank);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				db.close(pstmt,con); 
		}
		
		
	}
	
	public static void deleteStudent(MyFrame frame,String id){                                       //删除学生信息
		try {
			Connection con = db.getCon();
			
			String sql="select rank from db_student where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
		    ResultSet rs=pstmt.executeQuery();
		    while(rs.next()){
		        int rank=rs.getInt("rank");
		        new Methods().updateStudent(null,3,rank);
		      }
			
			sql = "delete from db_student where id=?";
			pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,id);
	        int result=pstmt.executeUpdate();
			if(result==1){
				JOptionPane.showMessageDialog(null,"删除成功！");
				frame.dispose();
			}
			else JOptionPane.showMessageDialog(null,"删除失败！");
			db.close(pstmt, con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeStudentMessage(MyFrame frame,String sql,String one,String two,String three,String four){               //修改学生信息
		int result=1;
		char[] age0=three.toCharArray();
		for(int i=0;i<age0.length;i++){
			if(age0[i]<48||age0[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		if(result==1){
			int age=0;
			if(!four.equals(""))age=Integer.parseInt(three);
		try {
			Connection con = db.getCon();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,one);
	        pstmt.setString(2, two);
	        pstmt.setString(3, three);
	        pstmt.setInt(4,age);
	        result=pstmt.executeUpdate();
	        if(result==1) {
	        	JOptionPane.showMessageDialog(null,"修改成功！");
	        	frame.dispose();
	        }
	        else JOptionPane.showMessageDialog(null,"修改失败！");
			db.close(pstmt, con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else JOptionPane.showMessageDialog(null,"输入数据有误");
	}
	
	
	public void changeStudentGrade(MyFrame frame,String sql,String one,String two,String three,String id){ //修改学生成绩
		int result=1;
		char[] chinese0=one.toCharArray();
		char[] math0=two.toCharArray();
		char[] english0=three.toCharArray();
		for(int i=0;i<chinese0.length;i++){
			if(chinese0[i]<48||chinese0[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		for(int i=0;i<math0.length;i++){
			if(math0[i]<48||math0[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		for(int i=0;i<english0.length;i++){
			if(english0[i]<48||english0[i]>57) result=0;
			if(i>2) {
				result=0;break;
			}
		}
		
		if(result==1){
			int chinese=0,math=0,english=0;
			if(!one.equals(""))chinese=Integer.parseInt(one);
			if(!two.equals(""))math=Integer.parseInt(two);
			if(!three.equals(""))english=Integer.parseInt(three);
				try {
					Connection con=db.getCon();
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,chinese);
					pstmt.setInt(2, math);
					pstmt.setInt(3, english);
					pstmt.setInt(4, chinese+math+english);
					pstmt.setString(5,id);
				    result=pstmt.executeUpdate();
				    if(result==1){
				    	JOptionPane.showMessageDialog(null,"修改成功！");
				    	sql="select rank from db_student where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1,id);
					    ResultSet rs=pstmt.executeQuery();
					    while(rs.next()){
					        int rank=rs.getInt("rank");
					        new Methods().updateStudent(id,2,rank);
					      }
				    	frame.dispose();
				    	}
				    else JOptionPane.showMessageDialog(null,"修改失败！");
					db.close(pstmt, con);
				} catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			else JOptionPane.showMessageDialog(null,"输入数据有误");
	}
	
	
	
	public String[] checkStudent(String id){                                                //查询学生信息
		String[] student=new String[10];
		if(id.isEmpty()) JOptionPane.showMessageDialog(null,"请输入学号");
		else {
			try {
			Connection con = db.getCon();
			String sql="select * from db_student where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				rs.previous();
				while(rs.next()){
					student[0]=(rs.getString("id"));
					student[1]=(rs.getString("name"));
					student[2]=(rs.getString("sex"));
					student[3]=(rs.getInt("age")+"");
					student[4]=(rs.getInt("chinese")+"");
					student[5]=(rs.getInt("math")+"");
					student[6]=(rs.getInt("english")+"");
					student[7]=(rs.getInt("score")+"");
					student[8]=(rs.getInt("rank")+"");
				}
			}
			else JOptionPane.showMessageDialog(null,"查无此人！");
			db.close(pstmt, con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return student;
	}
	
	
	
	
	public void fillTable(DefaultTableModel tableModel) throws Exception{                   //全体学生列表显示
		Connection con=db.getCon();
		String sql;
		sql="select * from db_student";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Vector v=new Vector();
			v.add(rs.getString("id"));
			v.add(rs.getString("name"));
			v.add(rs.getString("sex"));
			v.add(rs.getInt("age"));
			v.add(rs.getInt("chinese"));
			v.add(rs.getInt("math"));
			v.add(rs.getInt("english"));
			v.add(rs.getInt("score"));
			v.add(rs.getInt("rank"));
			tableModel.addRow(v);
		}
		db.close(pstmt, con);
	}
	
	
}
	



class BackgroundPanel extends JPanel  
{  
    Image img;  
    public BackgroundPanel(Image img)  
    {  
        this.img=img;  
        this.setOpaque(true);  
    }  
    
    public void paintComponent(Graphics g)  
    {  
        super.paintComponents(g);  
        g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);  
          
    }  
}  

