package swingstudy.ch04;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class DefaultButton {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("DefaultButton");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setLayout(new GridLayout(2, 2, 10, 10));

				JButton button1 = new JButton("Text Button");
				button1.setMnemonic(KeyEvent.VK_B);
				frame.add(button1);

				Icon warnIcon = new ImageIcon("image/warn.png");
				JButton button2 = new JButton(warnIcon);
				frame.add(button2);

				JButton button3 = new JButton("Warning", warnIcon);
				frame.add(button3);

				String htmlButton = "<html>" +
						"<sup>HTML</sup>" +
						"<sub><em>Button</em></sub>" +
						"<br>" + 
						"<span style=\"text-decoration: underline;\">Multi-line</span>" +
				"</html>";
				JButton button4 = new JButton(htmlButton);
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