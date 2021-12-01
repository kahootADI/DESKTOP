package kahootADI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.example.appkahootadi.AppServer;
import com.example.appkahootadi.TestService;

import dao.AnswerDao;
import dao.PlayerDao;
import model.Answer;
import model.Concourse;
import model.Player;
import model.Question;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuestionsRoom extends JFrame {

	private JPanel contentPane;
	private AppServer appServer;
	
	JButton answer;
	JButton answer2;
	JButton answer3;
	JButton answer4;
	static List<Answer> answers1;
	static Map<String, String> answerPlayer = new HashMap<String, String>();
	
	public static Map<String, String> getAnswerPlayer() {
		return answerPlayer;
	}

	public void setAnswerPlayer(Map<String, String> answerPlayer) {
		this.answerPlayer = answerPlayer;
	}

	boolean sPregunta = false;
	private static boolean panelQR;

	public static boolean isPanelQR() {
		return panelQR;
	}

	public void setPanelQR(boolean panelQR) {
		this.panelQR = panelQR;
	}

	public boolean issPregunta() {
		return sPregunta;
	}

	public void setsPregunta(boolean sPregunta) {
		this.sPregunta = sPregunta;
	}

	public static List<Answer> getAnswers1() {
		return answers1;
	}

	public void setAnswers1(ArrayList<Answer> answers1) {
		this.answers1 = answers1;
	}

	private waitingRoom wr;
	private TestService ts;

	// Countdown
	JButton nextQuestion;
	private Timer timer;
	private long startTime = -1;
	private long duration = Integer.parseInt(configClass.timeout) * 1000;
	JLabel countLabel;
	private JLabel question;
	Question actualQuestion;

	/**
	 * Launch the application.
	 */
	public static void QuestionRoomFrame(List<model.Question> questions) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionsRoom frame = new QuestionsRoom(questions);
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
	public QuestionsRoom(List<Question> questions) {
		panelQR = true;
		System.out.println(panelQR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Countdown();
		actualQuestion = questions.get(0);
		question = new JLabel(questions.get(0).getQuestion(), SwingConstants.CENTER);
		answers1 = AnswerDao.getAllAnswerByQuestion(questions.get(0).getId());

		JPanel answerPanel = new JPanel();
		contentPane.add(answerPanel, BorderLayout.CENTER);

		answer3 = new JButton();
		answer4 = new JButton();
		answer2 = new JButton();
		answer = new JButton();

		answer.setEnabled(false);
		answer.setVisible(false);

		answer2.setEnabled(false);
		answer2.setVisible(false);

		answer3.setEnabled(false);
		answer3.setVisible(false);

		answer4.setEnabled(false);
		answer4.setVisible(false);

		if (answers1.size() >= 2) {
			answer.setText(answers1.get(0).getAnswer());
			answer.setEnabled(false);
			answer.setVisible(true);
			answer.setBackground(Color.RED);

			answer2.setText(answers1.get(1).getAnswer());
			answer2.setEnabled(false);
			answer2.setVisible(true);
			answer2.setBackground(Color.BLUE);
		}
		if (answers1.size() >= 3) {
			answer3.setText(answers1.get(2).getAnswer());
			answer3.setEnabled(false);
			answer3.setVisible(true);
			answer3.setBackground(Color.GREEN);
		}
		if (answers1.size() == 4) {
			answer4.setText(answers1.get(3).getAnswer());
			answer4.setEnabled(false);
			answer4.setVisible(true);
			answer4.setBackground(Color.YELLOW);
		}

		GroupLayout gl_answerPanel = new GroupLayout(answerPanel);
		gl_answerPanel.setHorizontalGroup(gl_answerPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_answerPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_answerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_answerPanel.createSequentialGroup()
								.addComponent(answer, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(answer2, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
						.addGroup(gl_answerPanel.createSequentialGroup()
								.addComponent(answer3, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(answer4, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
								.addGap(30)))));
		gl_answerPanel.setVerticalGroup(gl_answerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_answerPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_answerPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(answer, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addComponent(answer2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_answerPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(answer3, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addComponent(answer4, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		answerPanel.setLayout(gl_answerPanel);

		JPanel rankingPanel = new JPanel();
		contentPane.add(rankingPanel, BorderLayout.EAST);

		nextQuestion = new JButton("SEGUENT PREGUNTA");
		nextQuestion.setEnabled(false);
		if (questions.size() - 1 == 0) {
			nextQuestion.setText("VEURE RESULTATS");
		}

//		nextQuestion.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				wr.pointsPlayer
//			}
//
//		});

		if (nextQuestion.getText().equals("SEGUENT PREGUNTA")) {
			nextQuestion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if (questions.size() > 1) {
						sPregunta = true;
						Concourse actualConcourse = waitingRoom.getConcourse();
						System.out.println("Ultimo concurso: " + actualConcourse.getId());
						PlayerDao playerDao = new PlayerDao();
						List<Player> actualPlayers = waitingRoom.getPlayers();
						for(int i = 0 ; i < actualPlayers.size(); i++) {
							System.out.println(actualPlayers.get(i));
						}
	                    for (String token : answerPlayer.keySet()) {
							for(Player p : actualPlayers) {
								if (token == p.getToken()) {
									PlayerDao playerDao2 = new PlayerDao();
									p.getAnswers().add(AnswerDao.getAnswerByNameAndQuestion(answerPlayer.get(token), actualQuestion.getId()));
									playerDao2.savePlayer(p);
								}
							}
						}
						questions.remove(0);
						panelQR = false;
						appServer = new AppServer();
						QuestionRoomFrame(questions);
					} else {
						System.exit(0);
					}
				}
			});
		}

		else if (nextQuestion.getText().equals("VEURE RESULTATS")) {
			nextQuestion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panelQR = false;
					dispose();
					rankingScreen.rankingScreenFrame();
				}
			});
		}

		JList<String> list = new JList<String>();

		DefaultListModel<String> modelo = new DefaultListModel<String>();

		for (Player p : waitingRoom.getPlayers()) {
			modelo.addElement(p.getUsername());
			list.setModel(modelo);
		}

		GroupLayout gl_rankingPanel = new GroupLayout(rankingPanel);
		gl_rankingPanel.setHorizontalGroup(gl_rankingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rankingPanel.createSequentialGroup().addContainerGap()
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_rankingPanel.createSequentialGroup().addGap(55)
						.addComponent(nextQuestion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(41)));
		gl_rankingPanel.setVerticalGroup(gl_rankingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rankingPanel.createSequentialGroup().addGap(18).addComponent(nextQuestion)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE).addContainerGap()));
		rankingPanel.setLayout(gl_rankingPanel);

		JPanel questionPanel = new JPanel();
		contentPane.add(questionPanel, BorderLayout.NORTH);

		countLabel = new JLabel("New label");
		countLabel.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_questionPanel = new GroupLayout(questionPanel);
		gl_questionPanel.setHorizontalGroup(gl_questionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_questionPanel.createSequentialGroup().addContainerGap()
						.addComponent(question, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(countLabel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_questionPanel.setVerticalGroup(gl_questionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_questionPanel.createParallelGroup(Alignment.BASELINE).addComponent(countLabel)
						.addComponent(question, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)));
		questionPanel.setLayout(gl_questionPanel);
	}

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
					nextQuestion.setEnabled(true);
					nextQuestion.setBackground(Color.ORANGE);
					answer.setVisible(false);
					answer2.setVisible(false);
					answer3.setVisible(false);
					answer4.setVisible(false);
					if (answers1.get(0).isCorrect()) {
						answer.setVisible(true);
					} else if (answers1.get(1).isCorrect()) {
						answer2.setVisible(true);
					} else if (answers1.get(2).isCorrect()) {
						answer3.setVisible(true);
					} else if (answers1.get(3).isCorrect()) {
						answer4.setVisible(true);
					}
				}
				SimpleDateFormat df = new SimpleDateFormat("ss");
				countLabel.setText(df.format(duration - clockTime));
			}
		});

		timer.setInitialDelay(0);
		timer.start();
	}
}
