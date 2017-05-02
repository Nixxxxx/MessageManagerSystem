package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.BookTypeDao;
import Model.BookType;
import Util.DbUtil;
import Util.StringUtil;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class BookTypeManageInterFrame extends JInternalFrame {
	private JTextField searchTxt;
	private JTable table;
	private JTextField idTxt;
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
					BookTypeManageInterFrame frame = new BookTypeManageInterFrame();
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
	public BookTypeManageInterFrame() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u578B\u7EF4\u62A4");
		setBounds(100, 100, 564, 650);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u578B\u540D\u79F0\uFF1A");
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookType("search");
			}
		});
		button.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/search.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button))
						.addComponent(scrollPane)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		
		JLabel label = new JLabel("\u7F16\u53F7\uFF1A");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u578B\u540D\u79F0\uFF1A");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setText("");
		bookTypeNameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u63CF\u8FF0\uFF1A");
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdate();
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/modify.png")));
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookTypeDelete();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInterFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addGap(50)
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(bookTypeNameTxt, 0, 0, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(66)
							.addComponent(btnNewButton)
							.addGap(108)
							.addComponent(btnNewButton_1)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u578B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u578B\u63CF\u8FF0"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(53);
		table.getColumnModel().getColumn(1).setPreferredWidth(115);
		table.getColumnModel().getColumn(2).setPreferredWidth(194);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		searchBookType("");
	}
	


	/**
	 * 图书类型查询
	 */
	private void searchBookType(String type) {
		Connection con=null;
		DefaultTableModel dtm=(DefaultTableModel) table.getModel(); 
		BookType bookType=new BookType(searchTxt.getText(),null);
		resetValues();
//		if(StringUtil.isEmpty(bookType.getBooktype())){
//			JOptionPane.showMessageDialog(null, "图书类型名称不能为空！");
//			return;
//		}
		dtm.setRowCount(0);
		try {
			con=dbUtil.getCon();
			ResultSet rs=BookTypeDao.search(con,bookType);
			if(!rs.next()) {
				if(type.equals("search"))
				JOptionPane.showMessageDialog(null,"无相关信息！");
				return;
			}
			rs.previous();
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("booktypename"));
				v.add(rs.getString("booktypedesc"));
				dtm.addRow(v);
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
	
	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		int row=table.getSelectedRow();
		idTxt.setText((String)table.getValueAt(row, 0));
		bookTypeNameTxt.setText((String)table.getValueAt(row, 1));
		bookTypeDescTxt.setText((String)table.getValueAt(row, 2));
	}
	
	/**
	 * 重置表单
	 */
	private void resetValues(){
		idTxt.setText("");
		bookTypeNameTxt.setText("");
		bookTypeDescTxt.setText("");
	}
	
	/**
	 * 图书类型修改
	 */
	private void bookTypeUpdate(){
		String id=idTxt.getText();
		String bookTypeName=bookTypeNameTxt.getText();
		String bookTypeDesc=bookTypeDescTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null,"编号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null,"图书类型名称不能为空！");
			return;
		}
		Connection con=null;
		BookType bookType=new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
		try {
			con=dbUtil.getCon();
			int result=BookTypeDao.isRepeat(con, bookType);
			if(result==-1){
				JOptionPane.showMessageDialog(null,"图书类型名称已存在！");
				return;
			}
			result=BookTypeDao.update(con, bookType);
			if(result==1){
				JOptionPane.showMessageDialog(null,"修改成功！");
				resetValues();
				searchBookType("");
			}
			else JOptionPane.showMessageDialog(null,"修改失败！");
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
	
	/**
	 * 图书类型删除
	 */
	private void bookTypeDelete(){
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null,"编号不能为空！");
			return;
		}
		Connection con=null;
		BookType bookType=new BookType(Integer.parseInt(id),null,null);
		try {
			con=dbUtil.getCon();
			ResultSet rs=new BookTypeDao().hasBook(con,bookType);
			if(rs.next()){
				JOptionPane.showMessageDialog(null,"此图书类型下有书！不可删除！");
				return;
			}else{
				int result=new BookTypeDao().delete(con,bookType);
				if(result==1){
					JOptionPane.showMessageDialog(null,"删除成功！");
					resetValues();
					searchBookType("");
				}else JOptionPane.showMessageDialog(null,"删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
