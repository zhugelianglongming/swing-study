/**
 * 
 */
package swingstudy.ch02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author lenovo
 *
 */
public class ButtonSample2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Button Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JButton button = new JButton("Select Me");

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("I was selected");
					}
				};

				MouseListener mouseListener = new MouseAdapter() {
					public void mousePressed(MouseEvent event) {
						int modifiers = event.getModifiers();

						if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
							System.out.println("Left button is pressed");
						}

						if ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
							System.out.println("Middle button is pressed");
						}

						if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
							System.out.println("Right button is pressed");
						}
					}

					public void mouseReleased(MouseEvent event) {
						if (SwingUtilities.isLeftMouseButton(event)) {
							System.out.println("Left button is released");
						}

						if (SwingUtilities.isMiddleMouseButton(event)) {
							System.out.println("Middle button is released");
						}

						if (SwingUtilities.isRightMouseButton(event)) {
							System.out.println("Right button is released");
						}
					}
				};

				button.addActionListener(actionListener);
				button.addMouseListener(mouseListener);

				frame.add(button, BorderLayout.SOUTH);
				frame.setSize(300, 100);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}