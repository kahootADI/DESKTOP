package kahootADI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class errorDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String errorMessage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					errorDisplay frame = new errorDisplay(errorMessage);
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
	public errorDisplay(String errorMessage) {
		
		JOptionPane.showMessageDialog(new JFrame(), errorMessage, "ERROR!", JOptionPane.ERROR_MESSAGE);
	}

}
