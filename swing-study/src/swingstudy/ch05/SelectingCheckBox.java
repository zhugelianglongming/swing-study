package swingstudy.ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SelectingCheckBox {

	private static String DESELECTED_LABEL = "Deselected";
	private static String SELECTED_LABEL = "Selected";

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Selecting CheckBox");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JCheckBox checkBox = new JCheckBox(DESELECTED_LABEL);

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						AbstractButton abstractButton = (AbstractButton) event.getSource();
						boolean selected = abstractButton.isSelected();
						String newLabel = (selected ? SELECTED_LABEL : DESELECTED_LABEL);
						abstractButton.setText(newLabel);
						System.out.println("Action: selected=" + selected);
					}
				};

				ChangeListener changeListener = new ChangeListener() {
					public void stateChanged(ChangeEvent event) {
						AbstractButton abstractButton = (AbstractButton) event.getSource();
						ButtonModel buttonModel = abstractButton.getModel();
						boolean armed = buttonModel.isArmed();
						boolean pressed = buttonModel.isPressed();
						boolean selected = buttonModel.isSelected();
						System.out.println("Changed: " + armed + "/" + pressed + "/" + selected);
					}
				};

				ItemListener itemListener = new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						AbstractButton abstractButton = (AbstractButton) event.getSource();
						Color foreground = abstractButton.getForeground();
						Color background = abstractButton.getBackground();
						int state = event.getStateChange();
						if (state == ItemEvent.SELECTED) {
							abstractButton.setForeground(background);
							abstractButton.setBackground(foreground);
							System.out.println("Item  : Selected");
						} else {
							System.out.println("Item  : Deselected");
						}
					}
				};

				checkBox.addActionListener(actionListener);
				checkBox.addChangeListener(changeListener);
				checkBox.addItemListener(itemListener);

				checkBox.setMnemonic(KeyEvent.VK_S);

				frame.add(checkBox, BorderLayout.NORTH);

				frame.setSize(300, 100);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}