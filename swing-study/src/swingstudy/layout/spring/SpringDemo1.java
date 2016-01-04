package swingstudy.layout.spring;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SpringDemo1 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("SpringDemo1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		contentPane.add(new JLabel("Label: "));
		contentPane.add(new JTextField("Text field", 15));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}