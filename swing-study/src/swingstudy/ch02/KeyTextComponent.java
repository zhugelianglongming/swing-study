package swingstudy.ch02;

import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class KeyTextComponent extends JComponent {

	private ActionListener actionListenerList = null;

	public KeyTextComponent() {
		setBackground(Color.CYAN);
		KeyListener internalKeyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (actionListenerList != null) {
					int keyCode = event.getKeyCode();
					String keyText = KeyEvent.getKeyText(keyCode);
					ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, keyText);
					actionListenerList.actionPerformed(actionEvent);
				}
			}
		};

		MouseListener internalMouseListener = new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				requestFocusInWindow();
			}
		};

		addKeyListener(internalKeyListener);
		addMouseListener(internalMouseListener);
	}

	public void addActionListener(ActionListener actionListener) {
		actionListenerList = AWTEventMulticaster.add(actionListenerList, actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		actionListenerList = AWTEventMulticaster.remove(actionListenerList, actionListener);
	}

	public boolean isFocusable() {
		return true;
	}
}