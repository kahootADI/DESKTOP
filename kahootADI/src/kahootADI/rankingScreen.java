package kahootADI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.example.appkahootadi.AppServer;

import model.Player;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;

public class rankingScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rankingScreenFrame();
	}
	
	public static void rankingScreenFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rankingScreen frame = new rankingScreen();
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
	public rankingScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFirstPlayer = new JLabel("First winner");
		
		JLabel lblSecondPlayer = new JLabel("Second winner");
		
		JLabel lblThirdPlayer = new JLabel("Third winner");
		
		JLabel lbl1st = new JLabel("1st");
		lbl1st.setForeground(new Color(255, 215, 0));
		lbl1st.setFont(new Font("Lato Semibold", Font.BOLD | Font.ITALIC, 30));
		
		JLabel lbl2nd = new JLabel("2nd");
		lbl2nd.setForeground(new Color(192, 192, 192));
		lbl2nd.setFont(new Font("Lato Semibold", Font.BOLD | Font.ITALIC, 30));
		
		JLabel lbl3rd = new JLabel("3rd");
		lbl3rd.setForeground(new Color(139, 0, 0));
		lbl3rd.setFont(new Font("Lato Semibold", Font.BOLD | Font.ITALIC, 30));
		
		JLabel lblClassificacio = new JLabel("CLASSIFICACIÓ");
		lblClassificacio.setFont(new Font("Lato Semibold", Font.BOLD | Font.ITALIC, 40));
		
		JList list = new JList();
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		for (Player p : waitingRoom.getPlayers()) {
			modelo.addElement(p.getUsername());
			list.setModel(modelo);
		}
		
		JButton btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AppServer as = new AppServer();
				gestioKahoots gestiokahoots = new gestioKahoots();
				gestiokahoots.setVisible(true);
			}
		});
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(286)
					.addComponent(lbl1st, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblFirstPlayer)
					.addContainerGap(413, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(245)
					.addComponent(lblClassificacio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(302))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(144)
					.addComponent(lbl2nd)
					.addGap(18)
					.addComponent(lblSecondPlayer)
					.addContainerGap(539, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(451, Short.MAX_VALUE)
					.addComponent(lbl3rd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblThirdPlayer)
					.addGap(257))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(244)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
					.addComponent(btnSortir)
					.addGap(71))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSortir)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblClassificacio, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(59)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl1st, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstPlayer))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(28)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbl2nd)
										.addComponent(lblSecondPlayer)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(67)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbl3rd)
										.addComponent(lblThirdPlayer))))
							.addGap(46)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
