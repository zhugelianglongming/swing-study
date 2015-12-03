package swingstudy.ch05;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SelectingToggle {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Selecting Toggle");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JToggleButton toggleButton = new JToggleButton("Toggle Button");

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						AbstractButton abstractButton = (AbstractButton) event.getSource();
						boolean selected = abstractButton.getModel().isSelected();
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
						System.out.println("Change: " + armed + "/" + pressed + "/" + selected);
					}
				};

				ItemListener itemListener = new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						int state = event.getStateChange();
						if (state == ItemEvent.SELECTED) {
							System.out.println("Item  : Selected");
						} else {
							System.out.println("Item  : Deselected");
						}
					}
				};

				toggleButton.addActionListener(actionListener);
				toggleButton.addChangeListener(changeListener);
				toggleButton.addItemListener(itemListener);
				frame.add(toggleButton, BorderLayout.NORTH);
				frame.setSize(300, 125);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}