package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

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

public class BookAddInterFrame extends JInternalFrame {
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
		setBounds(100, 100, 574, 535);
		
		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u4F5C  \u8005\uFF1A");
		
		JLabel label_1 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u4F5C\u8005\u6027\u522B\uFF1A");
		
		JLabel label_2 = new JLabel("\u4EF7  \u683C\uFF1A");
		
		pressTxt = new JTextField();
		pressTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		
		man = new JRadioButton("\u7537");
		buttonGroup.add(man);
		man.setSelected(true);
		
		woman = new JRadioButton("\u5973");
		buttonGroup.add(woman);
		
		bookTypeJcb = new JComboBox();
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAdd();
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookAddInterFrame.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_4)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(lblNewLabel)
									.addComponent(lblNewLabel_1)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(authorTxt)
											.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(9)
											.addComponent(man)
											.addGap(18)
											.addComponent(woman)))
									.addGap(84)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bookTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(1)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_1)
												.addComponent(label_2))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
												.addComponent(pressTxt, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
								.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(164)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(pressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(woman)
						.addComponent(man)
						.addComponent(label_2)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addGap(30))
		);
		getContentPane().setLayout(groupLayout);

		fillBookType();
	}

	/**
	 * 重置事件
	 */
	private void resetValues() {
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		pressTxt.setText("");
		bookDescTxt.setText("");
		man.setSelected(true);;
		if(bookTypeJcb.getItemCount()>0)
			bookTypeJcb.setSelectedIndex(0);
	}

	/**
	 * 添加事件
	 */
	private void bookAdd() {
		String bookName=bookNameTxt.getText();
		String author=authorTxt.getText();
		String price=priceTxt.getText();
		String bookDesc=bookDescTxt.getText();
		String press=pressTxt.getText();
		
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
		Book book=new Book(bookName,press,sex,author,Float.parseFloat(price) ,bookDesc,bookTypeId);
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
