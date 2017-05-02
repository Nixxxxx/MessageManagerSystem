package Frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

public class AboutUsInterFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUsInterFrame frame = new AboutUsInterFrame();
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
	public AboutUsInterFrame() {
		getContentPane().setBackground(Color.PINK);
		
		JLabel lblWeCanDo = new JLabel("Nothing is impossible");
		lblWeCanDo.setForeground(Color.BLUE);
		lblWeCanDo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(lblWeCanDo)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(108)
					.addComponent(lblWeCanDo)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setTitle("\u5173\u4E8E\u6211\u4EEC");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

	}
}
