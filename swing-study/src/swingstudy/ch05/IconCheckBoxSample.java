package swingstudy.ch05;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
 
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import swingstudy.ch04.DiamondIcon;
 
 
public class IconCheckBoxSample {
 
	private static class CheckBoxIcon implements Icon {
		private ImageIcon checkedIcon = new ImageIcon("image/plus.png");
		private ImageIcon uncheckedIcon = new ImageIcon("image/minus.png");
 
		public void paintIcon(Component component, Graphics g, int x, int y) {
			AbstractButton abstractButton = (AbstractButton)component;
			ButtonModel buttonModel = abstractButton.getModel();
			g.translate(x, y);
			ImageIcon imageIcon = buttonModel.isSelected() ? checkedIcon : uncheckedIcon;
			Image image = imageIcon.getImage();
			g.drawImage(image, 0, 0, component);
			g.translate(-x, -y);
		}
 
		public int getIconWidth() {
			return 20;
		}
 
		public int getIconHeight() {
			return 20;
		}
	}

	public static void main(String[] args) {
 
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame =  new JFrame("Iconizing CheckBox");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
				Icon checked = new DiamondIcon(Color.BLACK, true);
				Icon unchecked = new DiamondIcon(Color.BLACK, false);
 
				JCheckBox aCheckBox1 = new JCheckBox("Pizza", unchecked);
				JCheckBox aCheckBox2 = new JCheckBox("Calzone");
				aCheckBox2.setIcon(unchecked);
				aCheckBox2.setSelectedIcon(checked);
 
				Icon checkBoxIcon = new CheckBoxIcon();
				JCheckBox aCheckBox3 = new JCheckBox("Anchovies", checkBoxIcon);
				JCheckBox aCheckBox4 = new JCheckBox("Stuffed Crust", checked);
 
				frame.setLayout(new GridLayout(0, 1));
				frame.add(aCheckBox1);
				frame.add(aCheckBox2);
				frame.add(aCheckBox3);
				frame.add(aCheckBox4);
 
				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};
 
		EventQueue.invokeLater(runner);
	}
 
}