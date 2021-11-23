package kahootADI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AnswerDao;
import dao.KahootDao;
import dao.QuestionDao;
import model.Answer;
import model.Kahoot;
import model.Question;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

public class createKahoot extends JFrame {

	private JPanel contentPane;
	private JTextField tfTitol;
	private JTextField tfRespuesta1;
	private JTextField tfRespuesta2;
	private JTextField tfRespuesta3;
	private JTextField tfRespuesta4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createKahoot createKahootframe = new createKahoot();
					createKahootframe.setVisible(true);
					createKahootframe.setTitle("Crear kahoot");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public createKahoot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Titol:");

		tfTitol = new JTextField();
		tfTitol.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Llista de preguntes");

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listPreguntes = new JList<String>(listModel);

		JLabel lblNovaPregunta = new JLabel("Nova pregunta");

		JTextArea taNovaPregunta = new JTextArea();

		JList listTemesAssociats = new JList();

		JLabel lblTemesAssociats = new JLabel("Temes associats");

		JLabel lblRespostes = new JLabel("Respostes");

		JLabel lblComEsContesta = new JLabel("Tipus de pregunta");

		JRadioButton rdbtnSelect1resp = new JRadioButton("Selecciona resposta/es");
		buttonGroup_1.add(rdbtnSelect1resp);
		rdbtnSelect1resp.setSelected(true);
//		JRadioButton rdbtnRedactantResp = new JRadioButton("Redactant la resposta");
//		buttonGroup_1.add(rdbtnRedactantResp);

		JButton btnGuardarNouKahoot = new JButton("Guardar nou kahoot");

		JCheckBox chckbxCorrecta1 = new JCheckBox("");
		buttonGroup.add(chckbxCorrecta1);

		JCheckBox chckbxCorrecta2 = new JCheckBox("");
		buttonGroup.add(chckbxCorrecta2);

		JCheckBox chckbxCorrecta3 = new JCheckBox("");
		buttonGroup.add(chckbxCorrecta3);

		JCheckBox chckbxCorrecta4 = new JCheckBox("");
		buttonGroup.add(chckbxCorrecta4);

		Map<JTextField, JCheckBox> answers = new HashMap<JTextField, JCheckBox>();

		JButton btnAfegirPregunta = new JButton("Afegir pregunta");
		btnAfegirPregunta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int count = 0;
				boolean insert = true;
				answers.put(tfRespuesta1, chckbxCorrecta1);
				answers.put(tfRespuesta2, chckbxCorrecta2);
				answers.put(tfRespuesta3, chckbxCorrecta3);
				answers.put(tfRespuesta4, chckbxCorrecta4);

				for (Map.Entry<JTextField, JCheckBox> entry : answers.entrySet()) {


					if (!entry.getKey().getText().isEmpty()) {
						count++;
						System.out.println("Respuesta: " + entry.getKey().getText() + " Correcta -> " + entry.getValue().isSelected());
					} else {
						if (entry.getValue().isSelected()) {
							insert = false;
						}
					}
				}
				if(taNovaPregunta.getText().isEmpty()) {
					String errorMessage = "Falta la pregunta";
					new errorDisplay(errorMessage).setVisible(true);
				}
				else if(buttonGroup.getSelection() == null ) {
					String errorMessage = "Seleccione una respuesta correcta";
					new errorDisplay(errorMessage).setVisible(true);
				}
				else if (count < 2) {
					String errorMessage = "Se necesitan minimo 2 respuestas";
					new errorDisplay(errorMessage).setVisible(true);
				} else if (insert == false) {
					String errorMessage = "Respuesta correcta vacia";
					new errorDisplay(errorMessage).setVisible(true);
				} else {
					System.out.println("Respuestas insertadas correctamente!");
					
					//Guardamos la pregunta
					QuestionDao questionDao = new QuestionDao();
					Question question = new Question(taNovaPregunta.getText());
					questionDao.saveQuestion(question);
					
					//Guardamos las respuestas
					AnswerDao answerDao = new AnswerDao();
					for (Map.Entry<JTextField, JCheckBox> entry : answers.entrySet()) {
						if (entry.getValue().isSelected()) {
							Answer answer = new Answer(entry.getKey().getText(), true, question);
							answerDao.saveAnswer(answer);
						} else {
							Answer answer = new Answer(entry.getKey().getText(), false, question);
							answerDao.saveAnswer(answer);
						}
					}
					listModel.addElement(taNovaPregunta.getText());
					taNovaPregunta.setText("");
					tfRespuesta1.setText("");
					tfRespuesta2.setText("");
					tfRespuesta3.setText("");
					tfRespuesta4.setText("");
					buttonGroup.clearSelection();
				}

			}
		});

		btnGuardarNouKahoot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (taNovaPregunta.getText().isEmpty()) {
					KahootDao kahootDao = new KahootDao();
					Kahoot kahoot = new Kahoot(tfTitol.getText(), logIn.getUserLogin());
					kahootDao.saveKahoot(kahoot);
					List<Question> questions = QuestionDao.getAllQuestionForNewKahoot();
					for (Question q : questions) {
						QuestionDao.UpdateQuestionKahoot(q, kahoot);
					}
					dispose();
					gestioKahoots.gestioKahootsFrame();
				} else {
					String errorMessage = "Guarda la ultima pregunta";
					new errorDisplay(errorMessage).setVisible(true);
				}
			}
		});
		tfRespuesta1 = new JTextField();
		tfRespuesta1.setColumns(10);

		tfRespuesta2 = new JTextField();
		tfRespuesta2.setColumns(10);

		tfRespuesta3 = new JTextField();
		tfRespuesta3.setColumns(10);

		tfRespuesta4 = new JTextField();
		tfRespuesta4.setColumns(10);

		JButton btnBack = new JButton("Tornar");
		btnBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				dispose();
				gestioKahoots.gestioKahootsFrame();

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(40)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(taNovaPregunta)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNovaPregunta)
										.addComponent(listPreguntes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblNewLabel_1)
										.addGroup(
												gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
														.addPreferredGap(
																ComponentPlacement.UNRELATED)
														.addComponent(
																tfTitol, GroupLayout.PREFERRED_SIZE, 339,
																GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
								.createSequentialGroup().addGap(67)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTemesAssociats).addComponent(lblRespostes)
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING)
												.addComponent(tfRespuesta1, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(listTemesAssociats, GroupLayout.DEFAULT_SIZE, 288,
																Short.MAX_VALUE)
														.addComponent(tfRespuesta2))
												.addComponent(tfRespuesta3, GroupLayout.DEFAULT_SIZE, 288,
														Short.MAX_VALUE)
												.addComponent(tfRespuesta4, GroupLayout.DEFAULT_SIZE, 288,
														Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(chckbxCorrecta3, GroupLayout.DEFAULT_SIZE, 23,
																Short.MAX_VALUE)
														.addComponent(chckbxCorrecta1, GroupLayout.DEFAULT_SIZE, 23,
																Short.MAX_VALUE)
														.addComponent(chckbxCorrecta2).addComponent(chckbxCorrecta4))))
								.addGap(31))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBack)
										.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(221, Short.MAX_VALUE)
						.addComponent(btnAfegirPregunta)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(12).addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING).addComponent(rdbtnSelect1resp)
//								.addComponent(rdbtnRedactantResp)
										.addComponent(lblComEsContesta)))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(178)
										.addComponent(btnGuardarNouKahoot)))
						.addGap(187)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(24)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel).addComponent(tfTitol, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1).addComponent(lblTemesAssociats)))
						.addComponent(btnBack))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(listPreguntes, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(listTemesAssociats, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNovaPregunta).addComponent(lblRespostes))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfRespuesta1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxCorrecta1))
						.addGap(6)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tfRespuesta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxCorrecta2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(chckbxCorrecta3)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(chckbxCorrecta4))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tfRespuesta3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(tfRespuesta4,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addComponent(taNovaPregunta))
				.addGap(63).addComponent(lblComEsContesta).addGap(18).addComponent(rdbtnSelect1resp)
				.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(rdbtnRedactantResp)
				.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAfegirPregunta).addComponent(btnGuardarNouKahoot))
				.addGap(31)));
		contentPane.setLayout(gl_contentPane);
	}
}
