package StudentMessageSystem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.print.attribute.standard.PrinterLocation;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


/*
 * 学生信息管理系统窗口类
 */

public class MyFrame extends JFrame{

	private static Util db=new Util();
	
	
		public void myFrame(){												//加载登录窗口		
			JLabel jl1=new JLabel("用户名");jl1.setFont(new Font("宋体",0,17));jl1.setForeground(Color.white);
			JLabel jl2=new JLabel("密码");jl2.setFont(new Font("宋体",0,17));jl2.setForeground(Color.white);
			JTextField jt=new JTextField (null);
			JPasswordField jp=new JPasswordField(null);
			JLabel top=new JLabel("学生信息管理系统");top.setFont(new Font("微软雅黑",1,43));top.setForeground(Color.PINK);
			JLabel low=new JLabel("V1.0");low.setFont(new Font("微软雅黑",1,20));low.setForeground(Color.PINK);
			
			JButton jb1=new JButton ("登录");
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						int result=0;
						if(jt.getText().isEmpty()||String.valueOf(jp.getPassword()).isEmpty()){
							JOptionPane.showMessageDialog(null,"用户名、密码不能为空！");
							result=-1;
						}
						else{
						Connection con=db.getCon();
						String sql="select * from db_admin";
						PreparedStatement pstmt=con.prepareStatement(sql);
						ResultSet rs=pstmt.executeQuery();
						while(rs.next()){
								if(rs.getString(1).equals(jt.getText())&&rs.getString(2).equals(String.valueOf(jp.getPassword()))){
									dispose();
									new MyFrame().myFrame0();
									result=1;break;
								}
							}	
						db.close(pstmt, con);
						}	
						if(result==0)JOptionPane.showMessageDialog(null,"用户名或密码错误！");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			JButton jb2=new JButton ("重置");
			jb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				jt.setText(null);
				jp.setText(null);
				}
			});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel (new GridLayout(2,2,0,20));
			p1.setBounds(100,270,250,70);
			p1.add(jl1);p1.add(jt); 
			p1.add(jl2);p1.add(jp);
			JPanel p2=new JPanel (new BorderLayout());
			p2.add(jb1,BorderLayout.WEST);
			p2.add(jb2,BorderLayout.EAST);
			p2.setBounds(125,400, 200,35);
			top.setBounds(50, 30, 350, 200);
			low.setBounds(200,175, 50, 30);
			top.setOpaque(false);
			p1.setOpaque(false);
			p2.setOpaque(false);
			add(top);add(low);add(p1);add(p2);
			
			
			BackgroundPanel bgp;                                                        //背景图片
		    bgp=new BackgroundPanel((new ImageIcon("images/bg.jpg")).getImage());  
		    bgp.setBounds(0,0,450,600);  
		    this.getContentPane().add(bgp);
			
			new Methods().setFrame(this, "学生信息管理系统——登录", 1);
	    }
		

		
		
		
		public void myFrame0(){										                  			//加载目录窗口							
			JButton jb1=new JButton("1：查询已有学生"); 
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new MyFrame().myFrame01();
				}
			});
			
			JButton jb2=new JButton("2:添加新的学生");
			jb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new MyFrame().myFrame02();
				}
			});
			
			JButton jb3=new JButton("3:学生成绩排行");
			jb3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new MyFrame().myFrame03();
				}
			});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel(new GridLayout(3,1,15,30));
			p1.setBounds(125, 175, 200, 200);
			p1.add(jb1);p1.add(jb2);p1.add(jb3);
			p1.setOpaque(false);
			add(p1,BorderLayout.CENTER);
			
			new Methods().setFrame(this, "学生信息管理系统——目录", 1);
	    }
		
		
		
		
		
		public void myFrame01(){                                                                   //查询已有学生
			JLabel jl=new JLabel("学号");jl.setFont(new Font("宋体",0,17));jl.setForeground(Color.white);
			JTextField jt=new JTextField();
			
			JButton jb1=new JButton("查询学生信息");
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String[] student=new Methods().checkStudent(jt.getText());
					if(student[0]!=null) new MyFrame().myFrame011(student);
				}
			});
			
			JButton jb2=new JButton("查询学生成绩");
			jb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String[] student=new Methods().checkStudent(jt.getText());
					if(student[0]!=null) new MyFrame().myFrame012(student);
				}
			});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel(new GridLayout(1,2));
			p1.setBounds(100, 170,250,30);
			p1.add(jl);p1.add(jt);
			JPanel p2=new JPanel(new GridLayout(2,1,20,27));
			p2.setBounds(100, 280,250,100);
			p2.add(jb1);p2.add(jb2);
			add(p1);add(p2);
			p1.setOpaque(false);p2.setOpaque(false);
			
			new Methods().setFrame(this, "查询已有学生", 0);
		}
		
		
		
		
		public void myFrame011(String[] student){				                               //学生信息查看修改及删除	
			MyFrame frame=this;
			JLabel jl1=new JLabel("学号");jl1.setFont(new Font("宋体",0,17));jl1.setForeground(Color.white);
			JLabel jt1=new JLabel(student[0]);jt1.setFont(new Font("宋体",0,17));jt1.setForeground(Color.white);
			JLabel jl2=new JLabel("姓名");jl2.setFont(new Font("宋体",0,17));jl2.setForeground(Color.white);
			JTextField jt2=new JTextField();
			JLabel jl3=new JLabel("性别");jl3.setFont(new Font("宋体",0,17));jl3.setForeground(Color.white);
			JComboBox jt3=new JComboBox(); 
			jt3.addItem("");
	        jt3.addItem("男");  
	        jt3.addItem("女");
			JLabel jl4=new JLabel("年龄");jl4.setFont(new Font("宋体",0,17));jl4.setForeground(Color.white);
			JTextField jt4=new JTextField();
			
			jt2.setText(student[1]);
			if(student[2].equals("男"))
			jt3.setSelectedIndex(1);
			else if(student[2].equals("女"))
				jt3.setSelectedIndex(2);
			else jt3.setSelectedIndex(0);
			jt4.setText(student[3]);
				
			JButton jb1=new JButton("修改");
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String sql = "update db_student set name=?,sex=?,age=? where id=?";
					new Methods().changeStudentMessage(frame,sql,jt2.getText(),(String) jt3.getSelectedItem(),jt4.getText(),student[0]);
				}
			});
			
			JButton jb2=new JButton("删除");
			jb2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new Methods().deleteStudent(frame,student[0]);
			}
		});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel(new GridLayout(4,2,0,60));
			p1.add(jl1);p1.add(jt1);
			p1.add(jl2);p1.add(jt2);
			p1.add(jl3);p1.add(jt3);
			p1.add(jl4);p1.add(jt4);
			p1.setBounds(100,80, 250,300);
			JPanel p2=new JPanel(new GridLayout(1,2,75,0));
			p2.add(jb1);p2.add(jb2);
			p2.setBounds(73,460, 300, 35);
			add(p1);add(p2);
			p1.setOpaque(false);p2.setOpaque(false);
			
			new Methods().setFrame(this, "学生信息管理", 0);
	    }
		
		
		
		public void myFrame012(String[] student){											        		//学生成绩查看及修改
			MyFrame frame=this;
			JLabel jl1=new JLabel("学号");jl1.setFont(new Font("宋体",0,17));jl1.setForeground(Color.white);
			JLabel jla=new JLabel(student[0]);jla.setFont(new Font("宋体",0,17));jla.setForeground(Color.white);
			JLabel jl2=new JLabel("姓名");jl2.setFont(new Font("宋体",0,17));jl2.setForeground(Color.white);
			JLabel jlb=new JLabel();jlb.setFont(new Font("宋体",0,17));jlb.setForeground(Color.white);
			JLabel jl3=new JLabel("语文成绩");jl3.setFont(new Font("宋体",0,17));jl3.setForeground(Color.white);
			JTextField jt3=new JTextField();
			JLabel jl4=new JLabel("数学成绩");jl4.setFont(new Font("宋体",0,17));jl4.setForeground(Color.white);
			JTextField jt4=new JTextField();
			JLabel jl5=new JLabel("英语成绩");jl5.setFont(new Font("宋体",0,17));jl5.setForeground(Color.white);
			JTextField jt5=new JTextField();
			JLabel jl6=new JLabel("总分");jl6.setFont(new Font("宋体",0,17));jl6.setForeground(Color.white);
			JLabel jt6=new JLabel();jt6.setFont(new Font("宋体",0,17));jt6.setForeground(Color.white);
			JLabel jl7=new JLabel("排名");jl7.setFont(new Font("宋体",0,17));jl7.setForeground(Color.white);
			JLabel jt7=new JLabel();jt7.setFont(new Font("宋体",0,17));jt7.setForeground(Color.white);
			
			jlb.setText(student[1]);
			jt3.setText(student[4]);
			jt4.setText(student[5]);
			jt5.setText(student[6]);
			jt6.setText(student[7]);
			jt7.setText(student[8]);
			
			
			JButton jb1=new JButton("修改");
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String sql = "update db_student set chinese=?,math=?,english=?,score=? where id=?";
					new Methods().changeStudentGrade(frame,sql,jt3.getText(),jt4.getText(),jt5.getText(),student[0]);
				}
			});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel(new GridLayout(7,2,0,30));
			p1.add(jl1);p1.add(jla);
			p1.add(jl2);p1.add(jlb);
			p1.add(jl3);p1.add(jt3);
			p1.add(jl4);p1.add(jt4);
			p1.add(jl5);p1.add(jt5);
			p1.add(jl6);p1.add(jt6);
			p1.add(jl7);p1.add(jt7);
			p1.setBounds(80, 50, 300, 400);
			JPanel p2=new JPanel(new GridLayout(1,1));
			p2.add(jb1);
			p2.setBounds(75,485,300,38);
			add(p1);add(p2);
			p1.setOpaque(false);p2.setOpaque(false);
			
			new Methods().setFrame(this, "学生成绩管理", 0);
	    }
		
		
		public void myFrame02(){                                                             //添加学生
			MyFrame frame=this;
			JLabel jl1=new JLabel("学号");jl1.setFont(new Font("宋体",0,17));jl1.setForeground(Color.white);
			JLabel jl2=new JLabel("姓名");jl2.setFont(new Font("宋体",0,17));jl2.setForeground(Color.white);
			JLabel jl3=new JLabel("性别");jl3.setFont(new Font("宋体",0,17));jl3.setForeground(Color.white);
			JLabel jl4=new JLabel("年龄");jl4.setFont(new Font("宋体",0,17));jl4.setForeground(Color.white);
			JLabel jl5=new JLabel("语文成绩");jl5.setFont(new Font("宋体",0,17));jl5.setForeground(Color.white);
			JLabel jl6=new JLabel("数学成绩");jl6.setFont(new Font("宋体",0,17));jl6.setForeground(Color.white);
			JLabel jl7=new JLabel("英语成绩");jl7.setFont(new Font("宋体",0,17));jl7.setForeground(Color.white);
			JTextField jt1=new JTextField();
			JTextField jt2=new JTextField();
			JComboBox jt3=new JComboBox();
			jt3.addItem("");
	        jt3.addItem("男");  
	        jt3.addItem("女"); 
			JTextField jt4=new JTextField(null);
			JTextField jt5=new JTextField(null);
			JTextField jt6=new JTextField(null);
			JTextField jt7=new JTextField(null);
			
			JButton jb1=new JButton ("添加");
			jb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new Methods().addStudent(frame,jt1.getText(),jt2.getText(),(String) jt3.getSelectedItem(),jt4.getText(),
							jt5.getText(),jt6.getText(),jt7.getText()); 
				}
			});
			
			this.getContentPane().setLayout(null);
			JPanel p1=new JPanel(new GridLayout(7,2,0,30));
			p1.add(jl1);p1.add(jt1);
			p1.add(jl2);p1.add(jt2);
			p1.add(jl3);p1.add(jt3);
			p1.add(jl4);p1.add(jt4);
			p1.add(jl5);p1.add(jt5);
			p1.add(jl6);p1.add(jt6);
			p1.add(jl7);p1.add(jt7);
			p1.setBounds(80, 50, 300, 400);
			jb1.setBounds(75,485,300,38);
			add(p1);add(jb1);
			p1.setOpaque(false);
			
			new Methods().setFrame(this, "添加学生", 0);
		}
		
		
		
		public void myFrame03(){                                                                                  //学生成绩信息列表
			JTable table=new JTable();
			String[] headers = { "学号", "姓名", "性别","年龄","语文成绩","数学成绩","英语成绩","总分","排名" };//  
			Object[][] cellData = null;                                                                 //表格初始化  
			myTableModel model = new myTableModel(cellData ,headers);                                    //
			table.setEnabled(true);                                                                         //表格不可编辑
			
			table = new JTable(model); 
			myTableModel tableModel=(myTableModel) table.getModel();
			try {
				new Methods().fillTable(tableModel); 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			table.getColumnModel().getColumn(0).setPreferredWidth(100);             //设置表格各列宽度
			table.getColumnModel().getColumn(2).setPreferredWidth(40);			  //
			table.getColumnModel().getColumn(3).setPreferredWidth(40);              //
			table.getColumnModel().getColumn(8).setPreferredWidth(35);              //
			 
			 
			 
			 JLabel jl=new JLabel("学生信息管理系统V1.0");
			 jl.setOpaque(false);					//设置JLabel透明
			 jl.setFont(new Font("微软雅黑",1,18));   //设置字体、样式（粗体等）、字号
			 jl.setForeground(Color.WHITE);          //设置JLabel字体颜色
			 JTextField jt=new JTextField();
			
			
			 JButton jb1=new JButton("查询学生信息");
				jb1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String[] student=new Methods().checkStudent(jt.getText());
						if(student[0]!=null) new MyFrame().myFrame011(student);
					}
				});
				
				JButton jb2=new JButton("查询学生成绩");
				jb2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String[] student=new Methods().checkStudent(jt.getText());
						if(student[0]!=null) new MyFrame().myFrame012(student);
					}
				});
			
			this.getContentPane().setLayout(null);
			JScrollPane sp = new JScrollPane(table);
			JPanel p1=new JPanel(new GridLayout(2,1));
			JPanel p=new JPanel();
			p.setBackground(Color.GRAY);
			sp.setBounds(0, 0, 450,470);
			p.setBounds(0,520,450,100);
			p1.setBounds(200,470, 250, 50);
			jt.setBounds(0,470, 200, 50);
			p.add(jl);
			p1.add(jb1);p1.add(jb2);
			add(sp);add(p);add(p1);add(jt);
			sp.setOpaque(false);p.setOpaque(false);
			
			
			new Methods().setFrame(this, "查看成绩排名", 0);
		}
		


		public static void main(String[] args){
			new MyFrame().myFrame();
		}
}










class myTableModel extends DefaultTableModel
{

	public myTableModel(Object[][] data, Object[] columnNames){
		super(data, columnNames);//这里一定要覆盖父类的构造方法，否则不能实例myTableModel
	}

	public boolean isCellEditable(int row, int column){
		return false;//父类的方法里面是 return true的，所以就可以编辑了~~~
	}
}


