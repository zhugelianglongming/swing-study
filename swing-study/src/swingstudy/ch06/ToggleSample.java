package swingstudy.ch06;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class ToggleSample {
	public static void main(String args[]) {
		JFrame frame = new JFrame("JToggleButtonMenuItem Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar bar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.setMnemonic('f');
		JMenuItem newItem = new JMenuItem("New", 'N');
		file.add(newItem);
		JMenuItem openItem = new JMenuItem("Open", 'O');
		file.add(openItem);
		JMenuItem closeItem = new JMenuItem("Close", 'C');
		file.add(closeItem);
		file.addSeparator();
		JMenuItem saveItem = new JMenuItem("Save", 'S');
		file.add(saveItem);
		file.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit", 'X');
		file.add(exitItem);
		bar.add(file);
		
		JMenu edit = new JMenu("Edit");
		JMenuItem cutItem = new JMenuItem("Cut", 'T');
		cutItem.setAccelerator(KeyStroke.getKeyStroke('X', Event.CTRL_MASK));
		edit.add(cutItem);
		JMenuItem copyItem = new JMenuItem("Copy", 'C');
		copyItem.setAccelerator(KeyStroke.getKeyStroke('C', Event.CTRL_MASK));
		edit.add(copyItem);
		JMenuItem pasteItem = new JMenuItem("Paste", 'P');
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('V', Event.CTRL_MASK));
		pasteItem.setEnabled(false);
		edit.add(pasteItem);
		edit.addSeparator();
		JMenuItem findItem = new JMenuItem("Find", 'F');
		findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		edit.add(findItem);
		edit.setMnemonic('e');
		
		Icon atIcon = new ImageIcon("image/at.jpg");
		JMenu findOptions = new JMenu("Options");
		findOptions.setIcon(atIcon);
		findOptions.setMnemonic('O');
		ButtonGroup directionGroup = new ButtonGroup();
		JRadioButtonMenuItem forward = new JRadioButtonMenuItem("Forward", true);
		findOptions.add(forward);
		directionGroup.add(forward);
		JRadioButtonMenuItem backward = new JRadioButtonMenuItem("Backward");
		findOptions.add(backward);
		directionGroup.add(backward);
		findOptions.addSeparator();
		JCheckBoxMenuItem caseItem = new JCheckBoxMenuItem("Case Insensitive");
		findOptions.add(caseItem);
		edit.add(findOptions);
		
		// Demonstration of JToggleButtonMenuItem
		JToggleButtonMenuItem toggleItem = new JToggleButtonMenuItem("Ballon Help");
		toggleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected");
			}
		});
		edit.add(toggleItem);
		bar.add(edit);
		
		frame.setJMenuBar(bar);
		frame.setSize(350, 250);
		frame.setVisible(true);
	}
}