package swingstudy.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeLayoutExample {
	public static JPanel createPanel() {
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new EdgeLayout());
		outerPanel.add(new JButton("West1"), EdgeLayout.WEST);
		outerPanel.add(new JButton("North1"), EdgeLayout.NORTH);
		outerPanel.add(new JButton("West2"), EdgeLayout.WEST);
		outerPanel.add(new JButton("North2"), EdgeLayout.NORTH);
		outerPanel.add(new JButton("East1"), EdgeLayout.EAST);
		outerPanel.add(new JButton("South1"), EdgeLayout.SOUTH);
		outerPanel.add(new JButton("West3"), EdgeLayout.WEST);
		outerPanel.add(new JButton("West4"), EdgeLayout.WEST);
		outerPanel.add(new JButton("South2"), EdgeLayout.SOUTH);
		outerPanel.add(new JButton("South3"), EdgeLayout.SOUTH);
		outerPanel.add(new JButton("Center1"), EdgeLayout.CENTER);
		return outerPanel;
	}

	public static void main(String[] a) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(1);
		f.add(createPanel());
		f.pack();
		f.setVisible(true);
	}
}

@SuppressWarnings("serial")
class EdgeLayout implements LayoutManager2, java.io.Serializable {
	private List<Component> components = new ArrayList<Component>();
	private HashMap<Component, Object> constraints = new HashMap<Component, Object>();

	public static final String CENTER = "center";
	public static final String NORTH = "north";
	public static final String SOUTH = "south";
	public static final String EAST = "east";
	public static final String WEST = "west";

	public void addLayoutComponent(Component comp, Object constraints) {
		synchronized (comp.getTreeLock()) {
			if (constraints instanceof String && comp != null) {
				this.components.add(comp);
				this.constraints.put(comp, constraints);
			} else {
				throw new IllegalArgumentException(
						"Invalid component constraints.");
			}
		}
	}

	public Dimension maximumLayoutSize(Container target) {
		// Return a very large size since this layout manager will fill all
		// available space.
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	public float getLayoutAlignmentX(Container target) {
		// Centered on the X
		return (float) 0.5;
	}

	public float getLayoutAlignmentY(Container target) {
		// Centered on the Y
		return (float) 0.5;
	}

	public void invalidateLayout(Container target) {
		// There is no caching in EdgeLayout to there is nothing to invalidate
	}

	public void addLayoutComponent(String name, Component comp) {
		throw new IllegalArgumentException(
				"EdgeLayout only supports addition with contraints.");
	}

	public void removeLayoutComponent(Component comp) {
		synchronized (comp.getTreeLock()) {
			this.components.remove(comp);
			this.constraints.remove(comp);
		}
	}

	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int width = 0;
			int height = 0;

			// Add the preferred widths of all EAST/WEST components
			// Add the preferred height of all NORTH/SOUTH components
			for (int i = 0; i < this.components.size(); i++) {
				Component c = (Component) this.components.get(i);
				if (this.constraints.get(c).equals(WEST)
						|| this.constraints.get(c).equals(EAST)) {
					width += c.getPreferredSize().getWidth();
				} else {
					height += c.getPreferredSize().getHeight();
				}
			}

			width += parent.getInsets().right + parent.getInsets().left;
			height += parent.getInsets().top + parent.getInsets().bottom;

			return new Dimension(width, height);
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int width = 0;
			int height = 0;

			// Add the minimum widths of all EAST/WEST components
			// Add the minimum height of all NORTH/SOUTH components
			for (int i = 0; i < this.components.size(); i++) {
				Component c = (Component) this.components.get(i);
				if (this.constraints.get(c).equals(WEST)
						|| this.constraints.get(c).equals(EAST)) {
					width += c.getMinimumSize().getWidth();
				} else {
					height += c.getMinimumSize().getHeight();
				}
			}

			width += parent.getInsets().right + parent.getInsets().left;
			height += parent.getInsets().top + parent.getInsets().bottom;

			return new Dimension(width, height);
		}
	}

	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int top = insets.top;
			int left = insets.left;

			Dimension minimumSize = minimumLayoutSize(parent);

			int height = minimumSize.height;
			int width = minimumSize.width;

			int availableHeight = parent.getHeight() - insets.bottom
					- insets.top;
			int availableWidth = parent.getWidth() - insets.left - insets.right;
			if (height < availableHeight) {
				height = availableHeight;
			}
			if (width < availableWidth) {
				width = availableWidth;
			}

			int bottom = availableHeight;
			int right = availableWidth;

			Dimension preferredSize = preferredLayoutSize(parent);

			int preferredWidthAvailable = width - preferredSize.width;
			int preferredHeightAvailable = height - preferredSize.height;

			Component centerComp = null;

			for (int i = 0; i < this.components.size(); i++) {
				Component c = (Component) this.components.get(i);
				String constraint = (String) this.constraints.get(c);

				if (constraint.equals(CENTER)) {
					centerComp = c;
				} else {
					int compHeight;
					int compWidth;
					int xOrigin;
					int yOrigin;

					if (constraint.equals(NORTH) || constraint.equals(SOUTH)) {
						compWidth = width;

						if (preferredHeightAvailable > 0) {
							int preferredHeightNeeded = c
									.getPreferredSize().height
									- c.getMinimumSize().height;
							if (preferredHeightAvailable > preferredHeightNeeded) {
								compHeight = c.getPreferredSize().height;
								preferredHeightAvailable -= preferredHeightNeeded;
							} else {
								compHeight = c.getMinimumSize().height
										+ preferredHeightAvailable;
								preferredHeightAvailable = 0;
							}
						} else {
							compHeight = c.getMinimumSize().height;
						}
						height = height - compHeight;

						xOrigin = left;

						if (constraint.equals(NORTH)) {
							yOrigin = top;
							top += compHeight;
						} else {
							yOrigin = bottom - compHeight;
							bottom = yOrigin;
						}
					} else {
						compHeight = height;
						if (preferredWidthAvailable > 0) {
							int preferredWidthNeeded = c
									.getPreferredSize().width
									- c.getMinimumSize().width;
							if (preferredWidthAvailable > preferredWidthNeeded) {
								compWidth = c.getPreferredSize().width;
								preferredWidthAvailable -= preferredWidthNeeded;
							} else {
								compWidth = c.getMinimumSize().width
										+ preferredWidthAvailable;
								preferredWidthAvailable = 0;
							}
						} else {
							compWidth = c.getMinimumSize().width;
						}
						width = width - compWidth;

						yOrigin = top;

						if (constraint.equals(WEST)) {
							xOrigin = left;
							left += compWidth;
						} else {
							xOrigin = right - compWidth;
							right = xOrigin;
						}
					}
					c.setSize(compWidth, compHeight);
					c.setBounds(xOrigin, yOrigin, compWidth, compHeight);
				}
				if (centerComp != null) {
					c.setSize(width, height);
					c.setBounds(left, top, width, height);
				}
			}
		}
	}
}