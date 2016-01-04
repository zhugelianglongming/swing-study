package swingstudy.layout.spring;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SpringGrid {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create the panel and populate it.
		JPanel panel = new JPanel(new SpringLayout());
		for (int i = 0; i < 9; i++) {
			JTextField textField = new JTextField(Integer.toString(i));

			// Make the 4th field extra big.
			if (i == 4) {
				textField.setText("This one is extra long.");
			}

			panel.add(textField);
		}

		// Lay out the panel.
		SpringLayoutUtilities.makeGrid(panel, 3, 3, // rows, cols
				5, 5, // initialX, initialY
				5, 5);// xPad, yPad

		// Create and set up the window.
		JFrame frame = new JFrame("SpringGrid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		panel.setOpaque(true); // content panes must be opaque
		frame.setContentPane(panel);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}