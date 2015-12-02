package swingstudy.ch02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ActionTester {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Action Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				final Action printAction = new PrintHelloAction();

				JMenuBar menuBar = new JMenuBar();
				JMenu menu = new JMenu("File");
				menuBar.add(menu);
				menu.add(new JMenuItem(printAction));

				JToolBar toolBar = new JToolBar();
				toolBar.add(new JButton(printAction));

				JButton enableButton = new JButton("Enable");
				ActionListener enableActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						printAction.setEnabled(true);
					}
				};
				enableButton.addActionListener(enableActionListener);

				JButton disableButton = new JButton("Disable");
				ActionListener disableActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						printAction.setEnabled(false);
					}
				};
				disableButton.addActionListener(disableActionListener);

				JButton relabelButton = new JButton("Relabel");
				ActionListener relabelActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						printAction.putValue(Action.NAME, "Hello, World");
					}
				};
				relabelButton.addActionListener(relabelActionListener);

				JPanel buttonPanel = new JPanel();
				buttonPanel.add(enableButton);
				buttonPanel.add(disableButton);
				buttonPanel.add(relabelButton);

				frame.setJMenuBar(menuBar);

				frame.add(toolBar, BorderLayout.SOUTH);
				frame.add(buttonPanel, BorderLayout.NORTH);

				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}