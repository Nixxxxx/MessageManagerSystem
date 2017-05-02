package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import Dao.BookTypeDao;
import Model.BookType;
import Util.DbUtil;
import Util.StringUtil;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

	/**
	 * 图书类别添加
	 * @param evt
	 */
public class BookTypeAddInterFrame extends JInternalFrame {
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;
	private DbUtil dbUtil=new DbUtil();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrame frame = new BookTypeAddInterFrame();
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
	public BookTypeAddInterFrame() {
		setTitle("\u56FE\u4E66\u7C7B\u578B\u6DFB\u52A0");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 522, 443);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u578B\u540D\u79F0");
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u578B\u63CF\u8FF0");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAdd(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeAddInterFrame.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookTypeNameTxt.setText("");
				bookTypeDescTxt.setText("");
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeAddInterFrame.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
								.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addGap(142))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 添加事件
	 * @param evt
	 */
	private void bookTypeAdd(ActionEvent evt) {
		String bookTypeName=this.bookTypeNameTxt.getText();
		String bookTypeDesc=this.bookTypeDescTxt.getText();
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		if(StringUtil.isEmpty(bookTypeName)){
			JOptionPane.showMessageDialog(null,"图书类型名不能为空！");
			return;
		}
			try {
			con=dbUtil.getCon();
			int result=BookTypeDao.add(con,bookType);
			if(result==-1)
				JOptionPane.showMessageDialog(null,"图书类型名已存在！");
			else if(result==1){
					JOptionPane.showMessageDialog(null,"添加成功！");
					resetValues();
				}
				else JOptionPane.showMessageDialog(null,"添加失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,"图书添加失败！");
			}finally{
				try {
					dbUtil.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 重置事件
	 */
	private void resetValues() {
		bookTypeNameTxt.setText("");
		bookTypeDescTxt.setText("");
	}
}
