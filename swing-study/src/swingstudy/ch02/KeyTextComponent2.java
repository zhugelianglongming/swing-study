package swingstudy.ch02;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

@SuppressWarnings("serial")
public class KeyTextComponent2 extends JComponent {

	private EventListenerList actionListenerList = new EventListenerList();

	public KeyTextComponent2() {
		setBackground(Color.CYAN);

		KeyListener internalKeyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (actionListenerList != null) {
					int keyCode = event.getKeyCode();
					String keyText = KeyEvent.getKeyText(keyCode);
					ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, keyText);
					fireActionPerformed(actionEvent);
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
		actionListenerList.add(ActionListener.class, actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		actionListenerList.remove(ActionListener.class, actionListener);
	}

	public void fireActionPerformed(ActionEvent event) {
		EventListener[] listenerList = actionListenerList.getListeners(ActionListener.class);
		for(int i=0, n=listenerList.length; i<n; i++){
			ActionListener al = (ActionListener) listenerList[i];
			al.actionPerformed(event);
		}
	}

	public boolean isFocusable() {
		return true;
	}
}