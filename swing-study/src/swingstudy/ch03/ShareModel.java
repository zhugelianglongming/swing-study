package swingstudy.ch03;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class ShareModel {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Share Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Container content = frame.getContentPane();

				JTextArea textarea1 = new JTextArea();
				Document document = textarea1.getDocument();

				JTextArea textarea2 = new JTextArea(document);
				JTextArea textarea3 = new JTextArea(document);

				content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

				content.add(new JScrollPane(textarea1));
				content.add(new JScrollPane(textarea2));
				content.add(new JScrollPane(textarea3));

				frame.setSize(300, 400);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}