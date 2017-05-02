package Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.UserDao;
import Model.User;
import Util.DbUtil;
import Util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 26));
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/logo.png")));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/userName.png")));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/reset.png")));
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordTxt, Alignment.LEADING)
								.addComponent(usernameTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
					.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(92, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
					.addGap(85))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(49))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 登录事件
	 * @param e
	 */
	protected void login(ActionEvent e) {
		String username=usernameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null,"用户名或密码不能为空！");
		}
		else{
			User user=new User(username,password);
			Connection con=null;
			try {
				con=dbUtil.getCon();
				User resultUtil=userDao.login(con,user);
				if(resultUtil==null) 
					JOptionPane.showMessageDialog(null,"用户名或密码错误！");
				else {
					dispose();
					new MainFrame().setVisible(true);
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				try {
					dbUtil.close(con);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 重置事件
	 * @param evt
	 */
	private void resetValue(ActionEvent evt) {
		this.usernameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
