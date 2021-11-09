package kahootADI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class gestioKahoots extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestioKahoots frame = new gestioKahoots();
					frame.setTitle("Gestio de kahoots");
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
	public gestioKahoots() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblKahoots = new JLabel("KAHOOTS");
		
		JList listKahoots = new JList();
		
		JLabel lblTemes = new JLabel("TEMES");
		
		JList listTemes = new JList();
		
		JLabel lblTemesSel = new JLabel("TEMES SELECCIONATS");
		
		JList listTemesSel = new JList();
		
		JButton btnVeureDetall = new JButton("Veure detall");
		
		JButton btnCrearKahoot = new JButton("Crear kahoot");
		
		JButton btnJugar = new JButton("Jugar");
		
		JButton btnFiltrarTema = new JButton("Filtrar per tema");
		
		JButton btnEditarTemes = new JButton("Editar temes");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblKahoots)
						.addComponent(listKahoots, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVeureDetall)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCrearKahoot)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(listTemesSel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblTemesSel)
									.addComponent(lblTemes)
									.addComponent(listTemes, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnEditarTemes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFiltrarTema, Alignment.LEADING))
							.addGap(41)))
					.addContainerGap(108, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(124)
					.addComponent(btnJugar, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(372))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKahoots)
						.addComponent(lblTemes))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(listTemes, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addComponent(lblTemesSel)
							.addGap(7)
							.addComponent(listTemesSel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addComponent(listKahoots, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVeureDetall)
								.addComponent(btnCrearKahoot))
							.addGap(18)
							.addComponent(btnJugar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnFiltrarTema)
							.addGap(18)
							.addComponent(btnEditarTemes)))
					.addGap(93))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
