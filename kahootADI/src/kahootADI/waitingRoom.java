package kahootADI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AnswerDao;
import dao.ConcourseDao;
import dao.PlayerDao;
import dao.QuestionDao;
import model.Answer;
import model.Concourse;
import model.Kahoot;
import model.Player;
import model.Question;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class waitingRoom extends JFrame {

	private JPanel contentPane;
	JLabel lblCountdown;
	private JList list;
	private DefaultListModel listModel;
	private JLabel lblIp;
	JLabel lblIPLabel;
	static waitingRoom frame;
	private boolean playing = true;
	private boolean started = false;
	private boolean firstAnswer = true;
	private long clockTime;
	private Kahoot kahoot = gestioKahoots.getSelectKahoot();
	QuestionsRoom qr;
	configClass cc;
	static ArrayList<Player> players = new ArrayList<Player>();
	Map<String, String> tokenPlayer = new HashMap<String, String>();
	Map<String, Long> pointsPlayer = new HashMap<String, Long>();
	static Concourse concourse;
	
	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public static Concourse getConcourse() {
		return concourse;
	}

	public void setConcourse(Concourse concourse) {
		waitingRoom.concourse = concourse;
	}

	/**
	 * Launch the application.
	 */
	public static void waitingRoomFrame() {
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

	public List<List<String>> getKahootAnswer() {
		List<Question> kahootQuestions = QuestionDao.getAllQuestionByKahoot(kahoot.getId());
		List<List<String>> allAnswersOfKahoot = new ArrayList();
		for (Question q : kahootQuestions) {
			List<Answer> questionAnswer = AnswerDao.getAllAnswerByQuestion(q.getId());
			List<String> answers = new ArrayList();
			for (Answer ans : questionAnswer) {
				answers.add(ans.getAnswer());
			}
			allAnswersOfKahoot.add(answers);
		}
		return allAnswersOfKahoot;
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

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public Map<String, String> getTokenPlayer() {
		return tokenPlayer;
	}

	public void setTokenPlayer(Map<String, String> tokenPlayer) {
		this.tokenPlayer = tokenPlayer;
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	


	public long getClockTime() {
		return clockTime;
	}

	public void setClockTime(long clockTime) {
		this.clockTime = clockTime;
	}

	
	public boolean isFirstAnswer() {
		return firstAnswer;
	}

	public void setFirstAnswer(boolean firstAnswer) {
		this.firstAnswer = firstAnswer;
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

		JLabel lblTitol = new JLabel(gestioKahoots.getSelectKahoot().getTitle());

		lblIPLabel = new JLabel("IP:");

		JLabel lblIp = new JLabel("");

		JLabel lblEsperantJugadors = new JLabel("ESPERANT JUGADORS...");
		lblEsperantJugadors.setFont(new Font("Dialog", Font.BOLD, 27));

		listModel = new DefaultListModel<>();
		list = new JList(listModel);
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) UIManager.getColor("Button.foreground")));

		JButton btnComenarKahoot = new JButton("COMEN\u00C7AR KAHOOT");
		btnComenarKahoot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Countdown();
				for (int i = 0; i < list.getModel().getSize(); i++) {
					for (String name : tokenPlayer.keySet()) {
						pointsPlayer.put(name, (long) 0);
						if (name == list.getModel().getElementAt(i).toString()) {
							PlayerDao playerDao = new PlayerDao();
							Player player = new Player(list.getModel().getElementAt(i).toString(), "87654321", tokenPlayer.get(name)); // Poner ip como token
							playerDao.savePlayer(player);
							players.add(player);
						}
					}
				}
			}
		});

		lblCountdown = new JLabel("");
		lblCountdown.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 50));
		lblCountdown.setForeground(Color.RED);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(340).addComponent(btnComenarKahoot)
						.addContainerGap(351, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(34)
						.addComponent(lblTitol, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(lblCountdown, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblIp, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE)
										.addGap(24))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblIPLabel, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE)
										.addGap(67))))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(137, Short.MAX_VALUE)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE).addGap(146))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(232, Short.MAX_VALUE)
						.addComponent(lblEsperantJugadors).addGap(257)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblIPLabel)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblIp, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCountdown, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTitol))
				.addGap(26).addComponent(lblEsperantJugadors).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(list, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE).addGap(39)
				.addComponent(btnComenarKahoot, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(36, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	private Timer timer;
	private long startTime = -1;
	private long duration = Integer.parseInt(cc.timeout) * 1000;

	public void Countdown() {
		timer = new Timer(0, new ActionListener() {
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
					List<Question> questions = QuestionDao
							.getAllQuestionByKahoot(gestioKahoots.getSelectKahoot().getId());
					ConcourseDao concourseDao = new ConcourseDao();
					concourse = new Concourse(gestioKahoots.getSelectKahoot());
					concourseDao.saveConcourse(concourse);

					// table player_concourse

					for (Player p : players) {
						PlayerDao playerDao = new PlayerDao();
						p.getConcourses().add(concourse);
						playerDao.updatePlayer(p);
					}
					QuestionsRoom.QuestionRoomFrame(questions);
					started = true;
					firstAnswer = false;
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
