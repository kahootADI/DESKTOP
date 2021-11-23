package kahootADI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.AnswerDao;
import dao.QuestionDao;
import model.Answer;

import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Question extends JFrame {

	public JPanel contentPane;
	JLabel countLabel;
	JButton nextQuestion;
	static Question frame;
	JButton answer1;
	JButton answer2;
	JButton answer3;
	JButton answer4;
	List<Answer> answers1;

	/**
	 * Launch the application.
	 * 
	 * @param questions
	 */

	public static void QuestionFrame(List<model.Question> questions) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Question(questions);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param questions
	 */
	public Question(List<model.Question> questions) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 850, 600);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			Countdown();
			JLabel question = new JLabel(questions.get(0).getQuestion(), SwingConstants.CENTER);
			answers1 = AnswerDao.getAllQuestionByKahoot(questions.get(0).getId());
			//Intentar pasar las respuestas mezcladas
			Collections.shuffle(answers1);
			answer1 = new JButton();
			answer1.setEnabled(false);
			answer1.setVisible(false);

			answer2 = new JButton();
			answer2.setEnabled(false);
			answer2.setVisible(false);

			answer3 = new JButton();
			answer3.setEnabled(false);
			answer3.setVisible(false);

			answer4 = new JButton();
			answer4.setEnabled(false);
			answer4.setVisible(false);

			if (answers1.size() >= 2) {
				answer1.setText(answers1.get(0).getAnswer());
				answer1.setEnabled(false);
				answer1.setVisible(true);
				answer1.setForeground(Color.white);
				answer1.setBackground(Color.RED);

				answer2.setText(answers1.get(1).getAnswer());
				answer2.setEnabled(false);
				answer2.setVisible(true);
				answer2.setForeground(Color.white);
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

			countLabel = new JLabel("");

			nextQuestion = new JButton("NEXT QUESTION");
			nextQuestion.setEnabled(false);
			if (questions.size() - 1 == 0) {
				nextQuestion.setText("Ver resultados");
			}
			nextQuestion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if(questions.size() > 1) {
						questions.remove(0);
						QuestionFrame(questions);
					}else {
						System.exit(0);
					}
				}
			});

			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout
					.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup().addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(answer2, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
											.addComponent(answer1, GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE))
									.addGap(6)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(answer3, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
											.addComponent(answer4, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
									.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup().addGap(28)
									.addComponent(countLabel, GroupLayout.PREFERRED_SIZE, 46,
											GroupLayout.PREFERRED_SIZE)
									.addGap(220).addComponent(question, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
									.addGap(127).addComponent(nextQuestion).addGap(50)));
			groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(countLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(nextQuestion)
							.addComponent(question, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(answer1, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
							.addComponent(answer3, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(answer4, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addComponent(answer2, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
					.addContainerGap()));
			getContentPane().setLayout(groupLayout);
			
			
		}


	private Timer timer;
	private long startTime = -1;
	private long duration = Integer.parseInt(configClass.timeout) * 1000;

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
					answer1.setVisible(false);
					answer2.setVisible(false);
					answer3.setVisible(false);
					answer4.setVisible(false);
					if(answers1.get(0).isCorrect()) {
						answer1.setVisible(true);
					}
					else if(answers1.get(1).isCorrect()) {
						answer2.setVisible(true);
					}
					else if(answers1.get(2).isCorrect()) {
						answer3.setVisible(true);
					}
					else if(answers1.get(3).isCorrect()) {
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
