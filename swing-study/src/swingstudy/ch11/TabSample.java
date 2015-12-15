package swingstudy.ch11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swingstudy.ch04.DiamondIcon;

public class TabSample {

	static Color colors[] = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA };

	static void add(JTabbedPane tabbedPane, String label, int mnemonic) {
		int count = tabbedPane.getTabCount();
		JButton button = new JButton(label);
		button.setBackground(colors[count]);
		tabbedPane.addTab(label, new DiamondIcon(colors[count]), button, label);
		tabbedPane.setMnemonicAt(count, mnemonic);
	}

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Tabbed Pane Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JTabbedPane tabbedPane = new JTabbedPane();
				tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				String titles[] = { "General", "Security", "Content", "Connection", "Programs", "Advanced" };
				int mnemonics[] = { KeyEvent.VK_G, KeyEvent.VK_S, KeyEvent.VK_C, KeyEvent.VK_0, KeyEvent.VK_P,
						KeyEvent.VK_A };
				for (int i = 0, n = titles.length; i < n; i++) {
					add(tabbedPane, titles[i], mnemonics[i]);
				}

				ChangeListener changeListener = new ChangeListener() {
					public void stateChanged(ChangeEvent event) {
						JTabbedPane sourceTabbedPane = (JTabbedPane) event.getSource();
						int index = sourceTabbedPane.getSelectedIndex();
						System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
					}
				};
				tabbedPane.addChangeListener(changeListener);

				frame.add(tabbedPane, BorderLayout.CENTER);
				frame.setSize(400, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}