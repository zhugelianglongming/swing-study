package swingstudy.ch06;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.AbstractButton;
import javax.swing.Icon;

public class DiamondAbstractButtonStateIcon implements Icon {
	private final int width = 10;
	private final int height = 10;
	private Color color;
	private boolean selected;
	private Polygon polygon;

	public DiamondAbstractButtonStateIcon(Color color) {
		this.color = color;
		initPolygon();
	}

	private void initPolygon() {
		polygon = new Polygon();
		int halfWidth = width / 2;
		int halfHeight = height / 2;
		polygon.addPoint(0, halfHeight);
		polygon.addPoint(halfWidth, 0);
		polygon.addPoint(width, halfHeight);
		polygon.addPoint(halfWidth, height);
	}

	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return height;
	}

	@Override
	public void paintIcon(Component component, Graphics g, int x, int y) {
		g.setColor(color);
		g.translate(x, y);
		if (component instanceof AbstractButton) {
			AbstractButton abstractButton = (AbstractButton) component;
			selected = abstractButton.isSelected();
		}
		if (selected) {
			g.fillPolygon(polygon);
		} else {
			g.drawPolygon(polygon);
		}
		g.translate(-x, -y);
	}
}