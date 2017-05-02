package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.BookDao;
import Dao.BookTypeDao;
import Model.Book;
import Model.BookType;
import Util.DbUtil;
import Util.StringUtil;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import java.awt.Font;

public class BookManageInterFrameId extends JInternalFrame {
	private DbUtil dbUtil=new DbUtil();
	private JTextField idSearchTxt;
	private JTable table;
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField pressTxt;
	private JTextField priceTxt;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	private JRadioButton man;
	private JRadioButton women;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrameId frame = new BookManageInterFrameId();
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
	public BookManageInterFrameId() {
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 811, 795);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		
		idSearchTxt = new JTextField();
		idSearchTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search("search");
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookManageInterFrameId.class.getResource("/images/search.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(112)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(idSearchTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(101))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(idSearchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		JLabel label_2 = new JLabel("\u7F16\u53F7\uFF1A");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u63CF\u8FF0\uFF1A");
		
		bookDescTxt = new JTextArea();
		
		JLabel lblNewLabel_3 = new JLabel("\u4F5C \u8005\uFF1A");
		
		JLabel lblNewLabel_4 = new JLabel("\u6027\u522B\uFF1A");
		
		JLabel label_3 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		
		JLabel label_4 = new JLabel("\u56FE\u4E66\u7C7B\u578B\uFF1A");
		
		JLabel label_5 = new JLabel("\u4EF7\u683C\uFF1A");
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		pressTxt = new JTextField();
		pressTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		man = new JRadioButton("\u7537");
		buttonGroup.add(man);
		
		women = new JRadioButton("\u5973");
		buttonGroup.add(women);
		
		bookTypeJcb = new JComboBox();
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookManageInterFrameId.class.getResource("/images/modify.png")));
		
		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button.setIcon(new ImageIcon(BookManageInterFrameId.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1)
									.addGap(13))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(authorTxt)
										.addComponent(pressTxt, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
									.addGap(54)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(87)
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(man)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(women))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3)
							.addGap(186)
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(72)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(122))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(man)
						.addComponent(lblNewLabel_4)
						.addComponent(women))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(label_5)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(button))
					.addGap(38))
		);
		panel.setLayout(gl_panel);
		
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
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u4F5C\u8005", "\u6027\u522B", "\u4EF7\u683C", "\u51FA\u7248\u793E", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u578B", "\u72B6\u6001"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(58);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(42);
		table.getColumnModel().getColumn(4).setPreferredWidth(54);
		table.getColumnModel().getColumn(5).setPreferredWidth(87);
		table.getColumnModel().getColumn(6).setPreferredWidth(171);
		table.getColumnModel().getColumn(7).setPreferredWidth(81);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		search("");
	}

	/**
	 * 鼠标点击事件
	 */
	protected void bookTableMousePress() {
		int row=table.getSelectedRow();
		idTxt.setText((String)table.getValueAt(row, 0));
		bookNameTxt.setText((String)table.getValueAt(row, 1));
		authorTxt.setText((String)table.getValueAt(row, 2));
		priceTxt.setText((String)table.getValueAt(row, 4));
		pressTxt.setText((String)table.getValueAt(row, 5));
		bookDescTxt.setText((String)table.getValueAt(row, 6));
		if(((String)table.getValueAt(row, 3)).equals("男"))
			man.setSelected(true);
		else if(((String)table.getValueAt(row, 3)).equals("女"))
				women.setSelected(true);
		else buttonGroup.clearSelection();
		
		fillBookType();
		String bookTypeName=(String)table.getValueAt(row, 7);
		int n=bookTypeJcb.getItemCount();
		for(int i=0;i<n;i++){
			BookType bookType=(BookType)bookTypeJcb.getItemAt(i);
			if(bookType.getBooktype().equals(bookTypeName))
				bookTypeJcb.setSelectedIndex(i);
		}
	}

	/**
	 * 修改事件
	 */
	private void update() {
		String id=idTxt.getText();
		String bookName=bookNameTxt.getText();
		String author=authorTxt.getText();
		String price=priceTxt.getText();
		String bookDesc=bookDescTxt.getText();
		String press=pressTxt.getText();
		if(StringUtil.isEmpty(idTxt.getText())){
			JOptionPane.showMessageDialog(null,"请选择您要修改的图书！");
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
			int result=BookDao.update(con,book);
			if(result==1){
				JOptionPane.showMessageDialog(null,"修改成功");
				resetValues();
				search("");
			}else JOptionPane.showMessageDialog(null,"修改失败！");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"修改失败！");
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除事件
	 */
	private void delete() {
		Connection con=null;
		if(StringUtil.isEmpty(idTxt.getText())){
			JOptionPane.showMessageDialog(null,"请选择您要删除的图书！");
			return;
		}else{
			try {
			con=dbUtil.getCon();
			int result=BookDao.delete(con,idTxt.getText());
			if(result==1){
				JOptionPane.showMessageDialog(null,"删除成功！");
				resetValues();
				search("");
			}else JOptionPane.showMessageDialog(null,"删除失败！");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"删除失败！");
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		
	}

	/**
	 * 查询事件
	 */
	private void search(String type) {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel(); 
		dtm.setRowCount(0);
		Book book=new Book(idSearchTxt.getText());
		resetValues();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=BookDao.searchWithId(con, book);
			if(type.equals("search")){
				if(!rs.next()){
					JOptionPane.showMessageDialog(null,"无相关图书信息！");
					return;
			}
			rs.previous();	
			}
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookname"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("price"));
				v.add(rs.getString("press"));
				v.add(rs.getString("bookdesc"));
				ResultSet rss=BookTypeDao.searchid(con,new BookType(Integer.parseInt(rs.getString("booktypeid")),"",""));
				while(rss.next()){
					v.add(rss.getString("booktypename"));
				}
				if(rs.getInt("conditions")==1)
					v.add("在库");
				else	v.add("借出");
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
	 * 重置事件
	 */
	private void resetValues(){
		idTxt.setText("");
		bookNameTxt.setText("");
		authorTxt.setText("");
		priceTxt.setText("");
		pressTxt.setText("");
		bookDescTxt.setText("");
		buttonGroup.clearSelection();
		bookTypeJcb.removeAllItems();
	}
	
	/**
	 * 下拉框填充
	 */
	private void fillBookType(){
		Connection con=null;
		BookType bookType=new BookType("",null);
		bookTypeJcb.removeAllItems();
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
