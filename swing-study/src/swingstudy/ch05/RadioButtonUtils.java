package swingstudy.ch05;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class RadioButtonUtils {

	private RadioButtonUtils() {}

	public static Container createRadioButtonGrouping(String elements[], String title) {
		JPanel panel = new JPanel(new GridLayout(0, 1));

		if (title != null) {
			Border border = BorderFactory.createTitledBorder(title);
			panel.setBorder(border);
		}

		ButtonGroup group = new ButtonGroup();
		JRadioButton aRadioButton;

		for (int i = 0, n = elements.length; i < n; i++) {
			aRadioButton = new JRadioButton(elements[i]);
			panel.add(aRadioButton);
			group.add(aRadioButton);
		}

		return panel;
	}
}