package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.xvolks.jnative.exceptions.NativeException;

import Dao.BookDao;
import EpcSDK.EpcSDKDll;
import Model.Book;
import Util.DbUtil;
import Util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookBorrowInterFrame extends JInternalFrame {
	private boolean readerSwitch0=false;
	private boolean readerSwitch1=false;
	private boolean comm=false;
	private Timer timerSingleLabel = new Timer(); 
	DbUtil dbUtil=new DbUtil();
	Date date=new Date();
	EpcSDKDll epc = new EpcSDKDll();
	
	private JLabel timeTxt;
	private JButton switchButton0;
	private JButton switchButton1;
	private JTextField studentIdTxt;
	private JTextField bookIdTxt;
	private JTable table;
	private JComboBox comJcb;
	private JComboBox ComJcb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBorrowInterFrame frame = new BookBorrowInterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookBorrowInterFrame() {
		setTitle("\u56FE\u4E66\u501F\u9605");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 833, 470);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7AEF\u53E3");
		lblNewLabel.setBounds(464, 89, 45, 18);
		getContentPane().add(lblNewLabel);
		
		 ComJcb = new JComboBox();
		ComJcb.setBounds(542, 86, 63, 24);
		getContentPane().add(ComJcb);
		
		switchButton0 = new JButton("");
		switchButton0.setBackground(UIManager.getColor("scrollbar"));
		switchButton0.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u5F00\u5173 (1).png")));
		switchButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readerSwitch("0",readerSwitch0);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NativeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		switchButton0.setBounds(660, 85, 39, 27);
		getContentPane().add(switchButton0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 38, 377, 367);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTableMousePress();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u7C4D\u7F16\u53F7", "\u4E66\u7C4D\u540D\u79F0", "\u501F\u9605\u4EBA", "\u501F\u9605\u65F6\u95F4"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u501F\u9605\u8BB0\u5F55", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(2, 17, 369, 373);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u751F\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setBounds(421, 142, 88, 18);
		getContentPane().add(lblNewLabel_1);
		
		JButton button = new JButton("\u501F\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookBorrow();
			}
		});
		button.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u4E66\u7C4D(1).png")));
		button.setBounds(458, 334, 86, 41);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD8\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookReturn();
			}
		});
		button_1.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u4E66\u7C4D\uFF082\uFF09.png")));
		button_1.setBounds(632, 334, 101, 41);
		getContentPane().add(button_1);
		
		studentIdTxt = new JTextField();
		studentIdTxt.setEnabled(false);
		studentIdTxt.setBounds(514, 139, 187, 24);
		getContentPane().add(studentIdTxt);
		studentIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		lblNewLabel_2.setBounds(429, 259, 80, 18);
		getContentPane().add(lblNewLabel_2);
		
		bookIdTxt = new JTextField();
		bookIdTxt.setBounds(516, 256, 185, 24);
		getContentPane().add(bookIdTxt);
		bookIdTxt.setColumns(10);
		
		timeTxt = new JLabel("\u65F6\u95F4");
		timeTxt.setBounds(476, 13, 164, 18);
		getContentPane().add(timeTxt);
		
		JButton clearButton0 = new JButton("\u6E05\u9664");
		clearButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearButton0();
			}
		});
		clearButton0.setBounds(726, 138, 75, 27);
		getContentPane().add(clearButton0);
		
		JButton clearButton1 = new JButton("\u6E05\u9664");
		clearButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearButton1();
			}
		});
		clearButton1.setBounds(726, 255, 77, 27);
		getContentPane().add(clearButton1);
		
		JLabel label = new JLabel("\u7AEF\u53E3");
		label.setBounds(464, 216, 45, 18);
		getContentPane().add(label);
		
		switchButton1 = new JButton("");
		switchButton1.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u5F00\u5173 (1).png")));
		switchButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readerSwitch("1",readerSwitch1);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NativeException e) {
					e.printStackTrace();
				}
			}
		});
		switchButton1.setBackground(SystemColor.scrollbar);
		switchButton1.setBounds(660, 213, 39, 27);
		getContentPane().add(switchButton1);
		
		comJcb = new JComboBox();
		comJcb.setBounds(542, 213, 63, 24);
		getContentPane().add(comJcb);

		comList(comJcb);comList(ComJcb);
		Timer timer = new Timer();  
        timer.schedule(new MyTaskTimeDisplay(), 1000, 1000);
		record();
	}

	/**
	 * 鼠标点击事件
	 */
	protected void bookTableMousePress() {
		int row=table.getSelectedRow();
		studentIdTxt.setText((String)table.getValueAt(row, 2));
		bookIdTxt.setText((String)table.getValueAt(row,0));
	}
	

	/**
	 * 还书事件处理
	 */
	private void bookReturn() {
		if(StringUtil.isEmpty(studentIdTxt.getText())){
			JOptionPane.showMessageDialog(null,"学生学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookIdTxt.getText())){
			JOptionPane.showMessageDialog(null,"图书编号不能为空！");
			return;
		}
		
		Connection con=null;
		Book book=new Book(bookIdTxt.getText(),studentIdTxt.getText(),date);
		try {
			con=dbUtil.getCon();
			int result=new BookDao().bookConditions(con,book);
			if(result==-1){
				JOptionPane.showMessageDialog(null,"图书不存在！");
				return;
			}else if(result==1){
				JOptionPane.showMessageDialog(null,"图书未借出！");
				return;
			}else{
				result=new BookDao().bookBorrower(con, book);
				if(result==-1){
					JOptionPane.showMessageDialog(null,"图书编号和学生编号不匹配！");
					return;
				}
				result=new BookDao().bookReturn(con,book);
				if(result==1){
					JOptionPane.showMessageDialog(null,"还书成功！");
					reset();
				}else {
					JOptionPane.showMessageDialog(null,"还书失败！");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"还书失败！");
		}
	}

	/**
	 * 借书事件处理
	 */
	private void bookBorrow() {
		if(StringUtil.isEmpty(studentIdTxt.getText())){
			JOptionPane.showMessageDialog(null,"学生学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookIdTxt.getText())){
			JOptionPane.showMessageDialog(null,"图书编号不能为空！");
			return;
		}
		
		Connection con=null;
		Book book=new Book(bookIdTxt.getText(),studentIdTxt.getText(),date);
		try {
			con=dbUtil.getCon();
			int result=new BookDao().bookConditions(con,book);
			if(result==-1){
				JOptionPane.showMessageDialog(null,"图书不存在！");
				return;
			}else if(result==0){
				JOptionPane.showMessageDialog(null,"图书已借出！");
				return;
			}else{
				result=new BookDao().bookBorrow(con,book);
				if(result==1){
					JOptionPane.showMessageDialog(null,"借书成功！");
					reset();
				}else {
					JOptionPane.showMessageDialog(null,"借书失败！");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"借书失败！");
		}
		
	}

	/**
	 * 读写器开关
	 * @throws NativeException 
	 * @throws IllegalAccessException 
	 */
	private void readerSwitch(String type,boolean readerSwitch) throws IllegalAccessException, NativeException {
		int result=0;
		if(!comm){
			result=epc.openComm(comSelect(ComJcb));
			if(result!=-1){
				comm=true;
			}else {
				JOptionPane.showMessageDialog(null,"打开端口失败！");
				return;
			}
		}
		if(!readerSwitch){
			if(type.equals("0")){
				switchButton0.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/开关.png")));
				readerSwitch0=true;
			}
			if(type.equals("1")){
				switchButton1.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/开关.png")));
				readerSwitch1=true;
			} 
			timerSingleLabel=new Timer();
			timerSingleLabel.schedule(new MyTaskSingleLabel(), 1000, 1000);
		}
		else{
			timerSingleLabel.cancel();
			readerSwitch=false;
			if(type.equals("0")){
				switchButton0.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u5F00\u5173 (1).png")));
				readerSwitch0=false;
			}
			if(type.equals("1")){
				switchButton1.setIcon(new ImageIcon(BookBorrowInterFrame.class.getResource("/images/\u5F00\u5173 (1).png")));
				readerSwitch1=false;
			}	
		}
	}
	
	/**
	 * 延时函数
	 * @author JH
	 *
	 */
	class MyTaskSingleLabel extends TimerTask{
		public void run(){
			try {
				singleLabel();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NativeException e) {
				e.printStackTrace();
			}
		}
	}
	
	class MyTaskTimeDisplay extends TimerTask{
		public void run(){
			timeDisplay();
		}
	}
	
	/**
	 * 借阅记录显示
	 */
	private void record(){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel(); 
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=new BookDao().bookRecord(con);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookname"));
				v.add(rs.getString("borrower"));
				v.add(rs.getString("outtime"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 识别单个标签
	 * @throws NativeException 
	 * @throws IllegalAccessException 
	 */
	private void singleLabel() throws IllegalAccessException, NativeException{
		if(!StringUtil.isEmpty(studentIdTxt.getText())||!readerSwitch0) 
			timerSingleLabel.cancel();
		else{
			 HashMap<String, String> singleTagMap = epc.identifySingleTag(0);
			 if (singleTagMap != null&&StringUtil.isEmpty(studentIdTxt.getText())) {
				 studentIdTxt.setText(singleTagMap.get("tagID").toString());
		}
		 }
	}
	
	/**
	 * 图书编号清除
	 */
	protected void clearButton1() {
		bookIdTxt.setText("");
	}

	/**
	 * 学生学号清除
	 */
	protected void clearButton0() {
		studentIdTxt.setText("");
		timerSingleLabel=new Timer();
		timerSingleLabel.schedule(new MyTaskSingleLabel(), 1000, 1000);
	}

	/**
	 * COM口下拉框
	 */
	public void comList(JComboBox jComboBox){
		jComboBox.addItem("COM1");
		jComboBox.addItem("COM2");
		jComboBox.addItem("COM3");
		jComboBox.addItem("COM4");
	}
	
	/**
	 * COM口选择
	 */
	public int comSelect(JComboBox jcomboBox){
		String com=(String) jcomboBox.getSelectedItem();
		String COM=com.charAt(3)+"";
		return Integer.parseInt(COM);
	}
	
	/**
	 * 重置界面
	 */
	private void reset(){
		studentIdTxt.setText("");
		bookIdTxt.setText("");
		record();
		timerSingleLabel=new Timer();
		timerSingleLabel.schedule(new MyTaskSingleLabel(), 1000, 1000);
	}
	/**
	 * 动态时间显示
	 */
	private void timeDisplay(){
			date=new Date();
			timeTxt.setText((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date));
			
	}
}
