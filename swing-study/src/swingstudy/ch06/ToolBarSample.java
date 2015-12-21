package swingstudy.ch06;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import swingstudy.ch04.DiamondIcon;

public class ToolBarSample {
	private static final int COLOR_POSITION = 0;
	private static final int STRING_POSITION = 1;
	static Object buttonColors[][] = {
			{ Color.RED, "RED" }, 
			{ Color.BLUE, "BLUE" }, 
			{ Color.GREEN, "GREEN" },
			{ Color.BLACK, "BLACK" }, 
			null, // separator
			{ Color.CYAN, "CYAN" } };

	public static class TheActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println(event.getActionCommand());
		}
	}

	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("JToolBar Example");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				ActionListener actionListener = new TheActionListener();
				JToolBar toolbar = new JToolBar();
				toolbar.setRollover(true);
				for (Object[] color : buttonColors) {
					if (color == null) {
						toolbar.addSeparator();
					} else {
						Icon icon = new DiamondIcon((Color) color[COLOR_POSITION], true, 20, 20);
						JButton button = new JButton(icon);
						button.setActionCommand((String) color[STRING_POSITION]);
						button.addActionListener(actionListener);
						toolbar.add(button);
					}
				}
				Action action = new ShowAction(frame);
				JButton button = new JButton(action);
				toolbar.add(button);
				
				Container contentPane = frame.getContentPane();
				contentPane.add(toolbar, BorderLayout.NORTH);
				
				JTextArea textArea = new JTextArea();
				JScrollPane pane = new JScrollPane(textArea);
				contentPane.add(pane, BorderLayout.CENTER);
				
				frame.setSize(350, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}