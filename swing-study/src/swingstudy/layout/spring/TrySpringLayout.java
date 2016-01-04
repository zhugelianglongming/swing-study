package swingstudy.layout.spring;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class TrySpringLayout {
	public static void main(String[] args) {
		JFrame aWindow = new JFrame("This is a Spring Layout");
		aWindow.setBounds(30, 30, 300, 300);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		Container content = aWindow.getContentPane();
		content.setLayout(layout);
		JButton[] buttons = new JButton[6];
		SpringLayout.Constraints constr = null;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("Press " + (i + 1));
			content.add(buttons[i]);
		}
		Spring xSpring = Spring.constant(5, 15, 25);
		Spring ySpring = Spring.constant(10, 30, 50);
		constr = layout.getConstraints(buttons[0]);
		constr.setX(xSpring);
		constr.setY(ySpring);
		// Hook buttons together with springs
		for (int i = 1; i < buttons.length; i++) {
			constr = layout.getConstraints(buttons[i]);
			layout.putConstraint(SpringLayout.WEST, buttons[i], xSpring,
					SpringLayout.EAST, buttons[i - 1]);
			layout.putConstraint(SpringLayout.NORTH, buttons[i], ySpring,
					SpringLayout.SOUTH, buttons[i - 1]);
		}
		aWindow.setVisible(true); // Display the window
	}
}