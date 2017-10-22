package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane =null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setAutoRequestFocus(false);
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 669);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("\u56FE\u4E66\u7C7B\u522B\u7BA1\u7406");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookTypeAddInterFrame bookTypeAddFrame=new BookTypeAddInterFrame();
				desktopPane.add(bookTypeAddFrame);
				bookTypeAddFrame.setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u7EF4\u62A4");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageInterFrame bookTypeManageInterFrame=new BookTypeManageInterFrame();
				desktopPane.add(bookTypeManageInterFrame);
				bookTypeManageInterFrame.setVisible(true);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		mnNewMenu_1.add(menuItem);
		
		JMenu mnNewMenu_2 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookManager.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u56FE\u4E66\u6DFB\u52A0");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookAddInterFrame bookAddInterFrame=new BookAddInterFrame();
				desktopPane.add(bookAddInterFrame);
				bookAddInterFrame.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u56FE\u4E66\u7BA1\u7406\uFF08\u5176\u4ED6\u67E5\u627E\uFF09");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManageInterFrameOthers bookManageInterFrame=new BookManageInterFrameOthers();
				desktopPane.add(bookManageInterFrame);
				bookManageInterFrame.setVisible(true);
			}
		});
		
		JMenuItem menuItem_2 = new JMenuItem("\u56FE\u4E66\u7BA1\u7406\uFF08\u7F16\u53F7\u67E5\u627E\uFF09");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookManageInterFrameId bookManageInterFrame=new BookManageInterFrameId();
				desktopPane.add(bookManageInterFrame);
				bookManageInterFrame.setVisible(true);
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u56FE\u4E66 .png")));
		mnNewMenu_2.add(menuItem_2);
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem menuItem_3 = new JMenuItem("\u56FE\u4E66\u501F\u9605");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookBorrowInterFrame bookBorrowInterFrame=new BookBorrowInterFrame();
				desktopPane.add(bookBorrowInterFrame);
				bookBorrowInterFrame.setVisible(true);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u56FE\u4E66\u501F\u9605 .png")));
		mnNewMenu.add(menuItem_3);
		
		JMenu menu = new JMenu("\u5176\u4ED6\u64CD\u4F5C");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/me.png")));
		menuBar.add(menu);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menu.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUsInterFrame aboutUsInternalFrame=new AboutUsInterFrame();
				desktopPane.add(aboutUsInternalFrame);
				aboutUsInternalFrame.setVisible(true);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result=JOptionPane.showConfirmDialog(null,"确定要退出系统？");
				if(result==0)
				dispose();
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
		);
		((JPanel)this.getContentPane()).setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDFV1.0");
		lblNewLabel.setBounds(483, 330, 433, 217);
		desktopPane.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u4E66\u7C4D\u7BA1\u7406.png")));
		contentPane.setLayout(gl_contentPane);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH );
	}
}
