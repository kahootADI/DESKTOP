package kahootADI;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class logIn extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField textPassword;
	private String user = "Daniel";
	private String password = "Daniel123";
	static logIn logInframe;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		configClass.main(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logInframe = new logIn();
					logInframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public logIn() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblUser = new JLabel("User:");

		textUser = new JTextField();
		textUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		textPassword = new JPasswordField();

		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String userText = textUser.getText();
				String userPassword = String.valueOf(textPassword.getPassword());

				System.out.println(userText);
				System.out.println(userPassword);

				if (userText.equals("Daniel") && userPassword.equals("Daniel123")) {

					logInframe.dispose();
					gestioKahoots.waitingRoomFrame();

				} else {
					String errorMessage = "Log in failed. Try again!";
					new errorDisplay(errorMessage).setVisible(true);
				}
			}
		});

		JButton btnRememberPass = new JButton("Remember Password");

		BufferedImage logInPic = ImageIO.read(new File("kahootIcon.png"));
		JLabel lblImageIcon = new JLabel(new ImageIcon(logInPic.getScaledInstance(200, 200, logInPic.SCALE_FAST)));
		lblImageIcon.setBounds(10, 10, 10, 10);
		getContentPane().add(lblImageIcon);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(128)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING).addComponent(lblPassword).addComponent(lblUser))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(textPassword)
						.addComponent(textUser, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
				.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(133, Short.MAX_VALUE)
						.addComponent(btnLogIn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE).addGap(89)
						.addComponent(btnRememberPass).addGap(138))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(234, Short.MAX_VALUE)
						.addComponent(lblImageIcon, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addGap(229)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(73, Short.MAX_VALUE)
				.addComponent(lblImageIcon, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE).addGap(42)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUser).addComponent(
						textUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(34).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnLogIn)
						.addComponent(btnRememberPass))
				.addGap(73)));
		contentPane.setLayout(gl_contentPane);
	}

}
