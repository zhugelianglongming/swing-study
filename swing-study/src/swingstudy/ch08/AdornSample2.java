package swingstudy.ch08;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class AdornSample2 {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JFrame frame = new JFrame("Adornment Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}