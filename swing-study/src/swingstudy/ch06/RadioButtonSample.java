package swingstudy.ch06;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class RadioButtonSample {
	static Icon threeIcon = new ImageIcon("3.gif");
	static Icon fourIcon = new ImageIcon("4.gif");
	static Icon fiveIcon = new ImageIcon("5.gif");
	static Icon sixIcon = new ImageIcon("6.gif");

	public static class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AbstractButton aButton = (AbstractButton) event.getSource();
			boolean selected = aButton.getModel().isSelected();
			System.out.println(event.getActionCommand() + " - selected? " + selected);
		}
	}

	public static class ButtonItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			AbstractButton aButton = (AbstractButton) event.getSource();
			int state = event.getStateChange();
			String selected = ((state == ItemEvent.SELECTED) ? "selected" : "not selected");
			System.out.println(aButton.getText() + " - selected? " + selected);
		}
	}

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				final ActionListener actionListener = new ButtonActionListener();
				final ItemListener itemListener = new ButtonItemListener();
				
				JFrame frame = new JFrame("Radio Menu Example");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JMenuBar menuBar = new JMenuBar();
				JMenu menu = new JMenu("Menu");
				ButtonGroup buttonGroup = new ButtonGroup();
				menu.setMnemonic(KeyEvent.VK_M);
				
				JRadioButtonMenuItem emptyMenuItem = new JRadioButtonMenuItem();
				emptyMenuItem.setActionCommand("Empty");
				emptyMenuItem.addActionListener(actionListener);
				buttonGroup.add(emptyMenuItem);
				menu.add(emptyMenuItem);
				
				JRadioButtonMenuItem oneMenuItem = new JRadioButtonMenuItem("Partridge");
				oneMenuItem.addActionListener(actionListener);
				buttonGroup.add(oneMenuItem);
				menu.add(oneMenuItem);
				
				JRadioButtonMenuItem twoMenuItem = new JRadioButtonMenuItem("Turtle Dove", true);
				twoMenuItem.addActionListener(actionListener);
				buttonGroup.add(twoMenuItem);
				menu.add(twoMenuItem);
				
				JRadioButtonMenuItem threeMenuItem = new JRadioButtonMenuItem("French Hens", threeIcon);
				threeMenuItem.addItemListener(itemListener);
				buttonGroup.add(threeMenuItem);
				menu.add(threeMenuItem);
				
				JRadioButtonMenuItem fourMenuItem = new JRadioButtonMenuItem("Calling Birds", fourIcon, true);
				fourMenuItem.addActionListener(actionListener);
				buttonGroup.add(fourMenuItem);
				menu.add(fourMenuItem);
				
				JRadioButtonMenuItem fiveMenuItem = new JRadioButtonMenuItem(fiveIcon);
				fiveMenuItem.addActionListener(actionListener);
				fiveMenuItem.setActionCommand("Rings");
				buttonGroup.add(fiveMenuItem);
				menu.add(fiveMenuItem);
				
				JRadioButtonMenuItem sixMenuItem = new JRadioButtonMenuItem(sixIcon, true);
				sixMenuItem.addActionListener(actionListener);
				sixMenuItem.setActionCommand("Geese");
				buttonGroup.add(sixMenuItem);
				menu.add(sixMenuItem);
				
				menuBar.add(menu);
				
				frame.setJMenuBar(menuBar);
				frame.setSize(350, 250);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}