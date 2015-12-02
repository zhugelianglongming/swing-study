package swingstudy.ch02;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerSample {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Hello world timer");
					}
				};

				Timer timer = new Timer(500, actionListener);
				timer.start();
			}
		};

		EventQueue.invokeLater(runner);
	}

}