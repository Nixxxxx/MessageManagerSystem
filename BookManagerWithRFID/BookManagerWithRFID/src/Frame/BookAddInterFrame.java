package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Dao.BookDao;
import Dao.BookTypeDao;
import Model.Book;
import Model.BookType;
import Util.DbUtil;
import Util.StringUtil;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class BookAddInterFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	private JTextField bookNameTxt;
	private JTextField pressTxt;
	private JTextField priceTxt;
	private JTextField authorTxt;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	private JRadioButton woman;
	private JRadioButton man;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField idTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrame frame = new BookAddInterFrame();
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
	public BookAddInterFrame() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u6DFB\u52A0");
		setBounds(100, 100, 574, 527);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		label.setBounds(23, 112, 75, 18);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setBounds(112, 109, 125, 24);
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u4F5C  \u8005\uFF1A");
		lblNewLabel.setBounds(23, 156, 61, 18);
		
		JLabel label_1 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label_1.setBounds(313, 156, 60, 18);
		
		JLabel lblNewLabel_1 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		lblNewLabel_1.setBounds(23, 212, 75, 18);
		
		JLabel label_2 = new JLabel("\u4EF7  \u683C\uFF1A");
		label_2.setBounds(311, 212, 61, 18);
		
		pressTxt = new JTextField();
		pressTxt.setBounds(394, 153, 137, 24);
		pressTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setBounds(394, 209, 72, 24);
		priceTxt.setColumns(10);
		
		authorTxt = new JTextField();
		authorTxt.setBounds(112, 153, 125, 24);
		authorTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		label_3.setBounds(311, 112, 75, 18);
		
		man = new JRadioButton("\u7537");
		man.setBounds(116, 208, 43, 27);
		buttonGroup.add(man);
		
		woman = new JRadioButton("\u5973");
		woman.setBounds(194, 208, 43, 27);
		buttonGroup.add(woman);
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setBounds(394, 109, 136, 24);
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		label_4.setBounds(23, 267, 75, 18);
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setBounds(108, 265, 422, 111);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setBounds(102, 419, 95, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAdd();
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setBounds(358, 419, 94, 36);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/reset.png")));
		getContentPane().setLayout(null);
		getContentPane().add(label_4);
		getContentPane().add(label);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(authorTxt);
		getContentPane().add(bookNameTxt);
		getContentPane().add(man);
		getContentPane().add(woman);
		getContentPane().add(label_3);
		getContentPane().add(bookTypeJcb);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(priceTxt);
		getContentPane().add(pressTxt);
		getContentPane().add(bookDescTxt);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
		
		JLabel label_5 = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		label_5.setFont(new Font("宋体", Font.BOLD, 17));
		label_5.setBounds(23, 48, 95, 18);
		getContentPane().add(label_5);
		
		idTxt = new JTextField();
		idTxt.setBounds(112, 46, 125, 24);
		getContentPane().add(idTxt);
		idTxt.setColumns(10);

		fillBookType();
	}

	/**
	 * 重置事件
	 */
	private void resetValues() {
		idTxt.setText("");
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		pressTxt.setText("");
		bookDescTxt.setText("");
		buttonGroup.clearSelection();
		if(bookTypeJcb.getItemCount()>0)
			bookTypeJcb.setSelectedIndex(0);
	}

	/**
	 * 添加事件
	 */
	private void bookAdd() {
		String id=idTxt.getText();
		String bookName=bookNameTxt.getText();
		String author=authorTxt.getText();
		String price=priceTxt.getText();
		String bookDesc=bookDescTxt.getText();
		String press=pressTxt.getText();
		
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null,"图书编号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null,"图书名称不能为空！");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null,"图书作者不能为空！");
			return;
		}
		if(StringUtil.isEmpty(press)){
			JOptionPane.showMessageDialog(null,"出版社不能为空！");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null,"图书价格不能为空！");
			return;
		}
		
		String sex="";
		if(man.isSelected()) sex="男";
		else sex="女";
		
		BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		
		Connection con=null;
		Book book=new Book(id,bookName,press,sex,author,Float.parseFloat(price) ,bookDesc,bookTypeId);
		try {
			con=dbUtil.getCon();
			int result=BookDao.add(con,book);
			if(result==1){
				JOptionPane.showMessageDialog(null,"添加成功");
				resetValues();
			}else JOptionPane.showMessageDialog(null,"添加失败！");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"添加失败！");
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 下拉框填充
	 */
	private void fillBookType(){
		Connection con=null;
		BookType bookType=new BookType("",null);
		try {
			con=dbUtil.getCon();
			ResultSet rs=BookTypeDao.search(con,bookType);
			while(rs.next()){
				bookType=new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBooktype(rs.getString("booktypename"));
				bookTypeJcb.addItem(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
