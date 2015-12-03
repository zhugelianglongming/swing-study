package swingstudy.ch04;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class ActionButtonSample {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("DefaultButton");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setLayout(new GridLayout(2, 2, 10, 10));

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String command = event.getActionCommand();
						System.out.println("Selected: " + command);
					}
				};

				JButton button1 = new JButton("Text Button");
				button1.setMnemonic(KeyEvent.VK_B);
				button1.setActionCommand("First");
				button1.addActionListener(actionListener);
				frame.add(button1);

				JButton button2 = new JButton("WarnIcon");
				button2.setActionCommand("Second");
				button2.addActionListener(actionListener);
				frame.add(button2);

				JButton button3 = new JButton("Warn");
				button3.setActionCommand("Third");
				button3.addActionListener(actionListener);
				frame.add(button3);

				String htmlButton = "HTMLButton" + "Multi-line";
				JButton button4 = new JButton(htmlButton);
				button4.setActionCommand("Fourth");
				button4.addActionListener(actionListener);
				frame.add(button4);

				JRootPane rootPane = frame.getRootPane();
				rootPane.setDefaultButton(button2);

				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}