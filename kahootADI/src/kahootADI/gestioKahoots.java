package kahootADI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.KahootDao;
import model.Kahoot;
import test.common.AppServer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class gestioKahoots extends JFrame {

	private JPanel contentPane;
	static logIn login;
	static Kahoot selectKahoot;
	
	public static void main(String[] args) throws IOException {
		 gestioKahootsFrame();
	}

	/**
	 * Launch the application.
	 */
	
	public static void gestioKahootsFrame(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestioKahoots gestioKahootsframe = new gestioKahoots();
					gestioKahootsframe.setTitle("Gestio de kahoots");
					gestioKahootsframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public gestioKahoots() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblKahoots = new JLabel("KAHOOTS");

		
		JLabel lblTemes = new JLabel("TEMES");
		
		JList listTemes = new JList();
		
		JLabel lblTemesSel = new JLabel("TEMES SELECCIONATS");
		
		JList listTemesSel = new JList();
		
		JButton btnVeureDetall = new JButton("Veure detall");
		
		JButton btnCrearKahoot = new JButton("Crear kahoot");
		btnCrearKahoot.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();
				createKahoot createkahoot = new createKahoot();
				createkahoot.setVisible(true);
				
			}
		});
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setEnabled(false);
		
		btnJugar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				AppServer appServer = new AppServer();				
			}
		});
		
		List<Kahoot> kahoots = KahootDao.getAllKahootByUser(logIn.getUserLogin().getId());
		JList<String> listKahoots = new JList<String>();
		@SuppressWarnings("rawtypes")
		DefaultListModel modelo = new DefaultListModel();
		for(int i = 0; i < kahoots.size(); i++) {
			modelo.addElement(kahoots.get(i).getTitle());
		}
		listKahoots.setModel(modelo);
		
		listKahoots.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				btnJugar.setEnabled(true);
				selectKahoot = KahootDao.getKahootByName(listKahoots.getSelectedValue());
			}
		});
		
	
		
		
		JButton btnFiltrarTema = new JButton("Filtrar per tema");
		
		JButton btnEditarTemes = new JButton("Editar temes");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblKahoots)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnVeureDetall, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnCrearKahoot, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
										.addComponent(listKahoots, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)))
					.addGap(107)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnFiltrarTema, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(listTemes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(listTemesSel, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(lblTemesSel)
						.addComponent(btnEditarTemes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTemes))
					.addGap(60))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(92, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblKahoots)
						.addComponent(lblTemes))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(listKahoots, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addComponent(listTemes, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTemesSel)
							.addGap(7)
							.addComponent(listTemesSel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVeureDetall)
						.addComponent(btnFiltrarTema)
						.addComponent(btnCrearKahoot))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditarTemes)
						.addComponent(btnJugar))
					.addGap(93))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public static Kahoot getSelectKahoot() {
		return selectKahoot;
	}

	public void setSelectKahoot(Kahoot selectKahoot) {
		this.selectKahoot = selectKahoot;
	}
	
}
