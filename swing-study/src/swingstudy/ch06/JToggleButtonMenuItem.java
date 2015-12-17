package swingstudy.ch06;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.ButtonModel;
import javax.swing.JToggleButton;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.MouseInputListener;

@SuppressWarnings("serial")
public class JToggleButtonMenuItem extends JToggleButton implements MenuElement {
	Color savedForeground = null;
	private static MenuElement NO_SUB_ELEMENTS[] = new MenuElement[0];

	public JToggleButtonMenuItem() {
		init();
	}

	public JToggleButtonMenuItem(String label) {
		super(label);
		init();
	}

	public JToggleButtonMenuItem(Action action) {
		super(action);
		init();
	}

	private void init() {
		updateUI();
		setRequestFocusEnabled(false);
		// Borrows heavily from BasicMenuUI
		MouseInputListener mouseInputListener = new MouseInputListener() {
			// If mouse release over this menu item, activate it
			@Override
			public void mouseReleased(MouseEvent event) {
				MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
				Point point = event.getPoint();
				if ((point.x >= 0) && (point.x < getWidth()) && (point.y >= 0) && (point.y < getHeight())) {
					menuSelectionManager.clearSelectedPath();
					// Component automatically handles "selection" at this point
					// doClick(0); // not necessary
				} else {
					menuSelectionManager.processMouseEvent(event);
				}
			}

			// if mouse moves over menu item, add to selection path, so it
			// becomes armed
			@Override
			public void mouseEntered(MouseEvent event) {
				MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
				menuSelectionManager.setSelectedPath(getPath());
			}

			// when mouse moves away from menu item, disarm it and select
			// something else
			@Override
			public void mouseExited(MouseEvent event) {
				MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
				MenuElement path[] = menuSelectionManager.getSelectedPath();
				if (path.length > 1) {
					MenuElement[] newPath = new MenuElement[path.length - 1];
					for (int i = 0, c = path.length - 1; i < c; i++) {
						newPath[i] = path[i];
					}
					menuSelectionManager.setSelectedPath(newPath);
				}
			}

			// pass along drag events
			@Override
			public void mouseDragged(MouseEvent event) {
				MenuSelectionManager.defaultManager().processMouseEvent(event);
			}

			@Override
			public void mouseClicked(MouseEvent event) {
			}

			@Override
			public void mousePressed(MouseEvent event) {
			}

			@Override
			public void mouseMoved(MouseEvent event) {
			}
		};
		addMouseListener(mouseInputListener);
		addMouseMotionListener(mouseInputListener);
	}

	/*
	 * @see javax.swing.MenuElement#getComponent()
	 */
	@Override
	public Component getComponent() {
		return this;
	}

	/*
	 * @see javax.swing.MenuElement#getSubElements()
	 */
	@Override
	public MenuElement[] getSubElements() {
		// No subElements
		return NO_SUB_ELEMENTS;
	}

	/*
	 * @see javax.swing.MenuElement#menuSelectionChanged(boolean)
	 */
	@Override
	public void menuSelectionChanged(boolean isIncluded) {
		ButtonModel model = getModel();
		// only change armed state if different
		if (model.isArmed() != isIncluded) {
			model.setArmed(isIncluded);
		}
		if (isIncluded) {
			savedForeground = getForeground();
			if (!savedForeground.equals(Color.BLUE)) {
				setForeground(Color.BLUE);
			} else {
				// in case foreground blue, use something different
				setForeground(Color.BLUE);
			}
		} else {
			setForeground(savedForeground);
			// if null, get foreground from installed look and feel
			if (savedForeground == null) {
				updateUI();
			}
		}
	}

	/*
	 * @see javax.swing.MenuElement#processKeyEvent(java.awt.event.KeyEvent,
	 * javax.swing.MenuElement[], javax.swing.MenuSelectionManager)
	 */
	@Override
	public void processKeyEvent(KeyEvent event, MenuElement[] path, MenuSelectionManager manager) {
		// if user presses space while menu item armed, select it
		if (getModel().isArmed()) {
			int keyChar = event.getKeyChar();
			if (keyChar == KeyEvent.VK_SPACE) {
				manager.clearSelectedPath();
				System.out.println("Selected: JToggleButtonMenuItem, by KeyEvent");
				doClick(0); // inherited from AbstractButton
			}
		}
	}

	/*
	 * @see javax.swing.MenuElement#processMouseEvent(java.awt.event.MouseEvent,
	 * javax.swing.MenuElement[], javax.swing.MenuSelectionManager)
	 */
	@Override
	public void processMouseEvent(MouseEvent event, MenuElement[] path, MenuSelectionManager manager) {
		// for when mouse dragged over menu and button released
		if (event.getID() == MouseEvent.MOUSE_RELEASED) {
			manager.clearSelectedPath();
			System.out.println("Selected: JToggleButtonMenuItem, by MouseEvent");
			doClick(0);
		}
	} 
	
	// Borrows heavily from BasicMenuItemUI.getPath()
	private MenuElement[] getPath() {
		MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
		MenuElement oldPath[] = menuSelectionManager.getSelectedPath();
		MenuElement newPath[];
		int oldPathLength = oldPath.length;
		if (oldPathLength == 0) {
			return new MenuElement[0];
		}
		Component parent = getParent();
		if (oldPath[oldPathLength - 1].getComponent() == parent) {
			// going deeper under the parent menu
			newPath = new MenuElement[oldPathLength + 1];
			System.arraycopy(oldPath, 0, newPath, 0, oldPathLength);
			newPath[oldPathLength] = this;
		} else {
			// sibling/child menu item currently selected
			int newPathPosition;
			for (newPathPosition = oldPath.length - 1; newPathPosition >= 0; newPathPosition--) {
				if (oldPath[newPathPosition].getComponent() == parent) {
					break;
				}
			}
			newPath = new MenuElement[newPathPosition + 2];
			System.arraycopy(oldPath, 0, newPath, 0, newPathPosition + 1);
			newPath[newPathPosition + 1] = this;
		}
		return newPath;
	}
}
