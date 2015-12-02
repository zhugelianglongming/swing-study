package swingstudy.ch04;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class AncestorSample {

	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Ancestor Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				AncestorListener ancestorListener = new AncestorListener() {
					public void ancestorAdded(AncestorEvent event) {
						System.out.println("Added");
					}

					public void ancestorMoved(AncestorEvent event) {
						System.out.println("Moved");
					}

					public void ancestorRemoved(AncestorEvent event) {
						System.out.println("Removed");
					}
				};
				frame.getRootPane().addAncestorListener(ancestorListener);
				frame.setSize(300, 200);
				frame.setVisible(true);
				frame.getRootPane().setVisible(false);
				frame.getRootPane().setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}