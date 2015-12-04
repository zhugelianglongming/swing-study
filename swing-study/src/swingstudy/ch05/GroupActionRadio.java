package swingstudy.ch05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GroupActionRadio {
	private static final String sliceOptions[] = { "4 slices", "8 slices", "12 slices", "16 slices" };
	private static final String crustOptions[] = { "Sicilian", "Thin Crust", "Thick Crust", "Stuffed Crust" };

	private static final String BUTTON = "Button-";
	private static final String SLICE = "Slice-";
	private static final String CRUST = "Crust-";
	private static final String ACTION = "Action:";
	private static final String ITEM = "Item  :";
	private static final String CHANGE = "Change:";

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Grouping Example");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ActionListener sliceActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						AbstractButton aButton = (AbstractButton) event.getSource();
						System.out.println(SLICE + ACTION + "Selected: " + aButton.getText());
					}
				};
				Container sliceContainer = RadioButtonUtils2.createRadioButtonGrouping(sliceOptions, "Slice Count",
						sliceActionListener);
				ActionListener crustActionListener = new ActionListener() {
					String lastSelected;

					public void actionPerformed(ActionEvent event) {
						AbstractButton aButton = (AbstractButton) event.getSource();
						String label = aButton.getText();
						String msgStart;
						if (label.equals(lastSelected)) {
							msgStart = "Reselected: ";
						} else {
							msgStart = "Selected: ";
						}
						lastSelected = label;
						System.out.println(CRUST + ACTION + msgStart + label);
					}
				};
				ItemListener itemListener = new ItemListener() {
					String lastSelected;

					public void itemStateChanged(ItemEvent event) {
						AbstractButton aButton = (AbstractButton) event.getSource();
						int state = event.getStateChange();
						String label = aButton.getText();
						String msgStart;
						if (state == ItemEvent.SELECTED) {
							if (label.equals(lastSelected)) {
								msgStart = "Reselected -> ";
							} else {
								msgStart = "Selected -> ";
							}
							lastSelected = label;
						} else {
							msgStart = "Deselected -> ";
						}
						System.out.println(CRUST + ITEM + msgStart + label);
					}
				};
				ChangeListener changeListener = new ChangeListener() {
					public void stateChanged(ChangeEvent event) {
						AbstractButton aButton = (AbstractButton) event.getSource();
						ButtonModel aModel = aButton.getModel();
						boolean armed = aModel.isArmed();
						boolean pressed = aModel.isPressed();
						boolean selected = aModel.isSelected();
						System.out.println(CRUST + CHANGE + armed + "/" + pressed + "/" + selected);
					}
				};
				final Container crustContainer = RadioButtonUtils2.createRadioButtonGrouping(crustOptions, "Crust Type",
						crustActionListener, itemListener, changeListener);
				ActionListener buttonActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Enumeration<String> selected = RadioButtonUtils2.getSelectedElements(crustContainer);
						while (selected.hasMoreElements()) {
							System.out.println(BUTTON + ACTION + "Selected -> " + selected.nextElement());
						}
					}
				};
				JButton button = new JButton("Order Pizza");
				button.addActionListener(buttonActionListener);
				frame.add(sliceContainer, BorderLayout.WEST);
				frame.add(crustContainer, BorderLayout.EAST);
				frame.add(button, BorderLayout.SOUTH);
				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
}