package swingstudy.ch02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonSample3 {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Button Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				final JButton button1 = new JButton("Select Me");
				final JButton button2 = new JButton("No Select Me");

				final Random random = new Random();

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JButton button = (JButton) event.getSource();
						int red = random.nextInt(255);
						int green = random.nextInt(255);
						int blue = random.nextInt(255);
						button.setBackground(new Color(red, green, blue));
					}
				};

				PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent event) {
						String property = event.getPropertyName();
						if ("background".equals(property)) {
							button2.setBackground((Color) event.getNewValue());
						}
					}
				};

				button1.addActionListener(actionListener);
				button1.addPropertyChangeListener(propertyChangeListener);
				button2.addActionListener(actionListener);

				frame.add(button1, BorderLayout.NORTH);
				frame.add(button2, BorderLayout.SOUTH);
				frame.setSize(300, 100);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}