package swingstudy.ch02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author Ã÷
 * Test code for both {@code keyTextComponent} and {@code keyTextComponent2} class.
 * You may change code to test another class
 */
public class KeyTextTester {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Key Text Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Change code to test a different class.
//				KeyTextComponent keyTextComponent = new KeyTextComponent();
				KeyTextComponent2 keyTextComponent = new KeyTextComponent2();
				final JTextField textField = new JTextField();

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String keyText = event.getActionCommand();
						textField.setText(keyText);
					}
				};

				keyTextComponent.addActionListener(actionListener);

				frame.add(keyTextComponent, BorderLayout.CENTER);
				frame.add(textField, BorderLayout.SOUTH);
				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}