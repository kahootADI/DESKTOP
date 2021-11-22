package kahootADI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import test.common.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.QuestionDao;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;

public class waitingRoom extends JFrame {
	private JPanel contentPane;
	JLabel lblCountdown;
	private JList list;
    private DefaultListModel listModel;
    private JLabel lblIp;
    JLabel lblIPLabel;
    static waitingRoom frame;

	/**
	 * Launch the application.
	 */
	public static void waitingRoomFrame(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new waitingRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

    

    public DefaultListModel getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

  
	public JLabel getLblIPLabel() {
		return lblIPLabel;
	}

	public void setLblIPLabel(JLabel lblIPLabel) {
		this.lblIPLabel = lblIPLabel;
	}

	/**
	 * Create the frame.
	 */
	public waitingRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitolDelKahoot = new JLabel("Titol del kahoot:");
		
		JLabel lblTitol = new JLabel(gestioKahoots.getSelectKahoot().getTitle());
		
		lblIPLabel = new JLabel("IP:");
		
		JLabel lblIp = new JLabel("");
		
		JLabel lblEsperantJugadors = new JLabel("ESPERANT JUGADORS...");
		lblEsperantJugadors.setFont(new Font("Dialog", Font.BOLD, 27));
		
		
		listModel = new DefaultListModel<>();
		list = new JList(listModel);
		
		JButton btnComenarKahoot = new JButton("COMENZAR KAHOOT");
		btnComenarKahoot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Countdown();
				
				
			}
		});
		
		lblCountdown = new JLabel("");
		lblCountdown.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 50));
		lblCountdown.setForeground(Color.RED);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(340)
					.addComponent(btnComenarKahoot)
					.addContainerGap(341, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(lblTitolDelKahoot)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitol, GroupLayout.PREFERRED_SIZE, lblTitol.getText().length(), GroupLayout.PREFERRED_SIZE)
					.addGap(218)
					.addComponent(lblCountdown, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblIp, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblIPLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(67))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
					.addGap(146))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(273, Short.MAX_VALUE)
					.addComponent(lblEsperantJugadors)
					.addGap(257))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTitolDelKahoot)
							.addComponent(lblTitol))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblIPLabel)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblIp, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblCountdown, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(lblEsperantJugadors)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnComenarKahoot, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private Timer timer;
    private long startTime = -1;
    private long duration =  Integer.parseInt(configClass.timeout) * 1000;
	
	public void Countdown() {
		timer = new Timer (0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
				
				long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
    		        List<model.Question> questions = QuestionDao.getAllQuestionByKahoot(gestioKahoots.getSelectKahoot().getId());
    		        Question.QuestionFrame(questions);
    		        dispose();
                }
                
                SimpleDateFormat df = new SimpleDateFormat("ss");
                lblCountdown.setText(df.format(duration - clockTime));
                
			}
		});
		
		timer.setInitialDelay(0);
		timer.start();
	}
}
