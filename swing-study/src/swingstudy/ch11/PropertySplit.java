package swingstudy.ch11;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import swingstudy.ch04.OvalPanel;

public class PropertySplit {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Property Split");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// create/configure split pane
				JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				splitPane.setContinuousLayout(true);
				splitPane.setOneTouchExpandable(true);

				// create top component
				JComponent topComponent = new OvalPanel();
				splitPane.setTopComponent(topComponent);

				// create bottom component
				JComponent bottomComponent = new OvalPanel();
				splitPane.setBottomComponent(bottomComponent);

				// create PropertyChangeListener
				PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent event) {
						JSplitPane sourceSplitPane = (JSplitPane) event.getSource();
						String propertyName = event.getPropertyName();
						if (propertyName.equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY)) {
							int current = sourceSplitPane.getDividerLocation();
							System.out.println("Current: " + current);
							Integer last = (Integer) event.getNewValue();
							System.out.println("Last: " + last);
							Integer priorLast = (Integer) event.getOldValue();
							System.out.println("Prior last: " + priorLast);
						}
					}
				};
				// attach listener
				splitPane.addPropertyChangeListener(propertyChangeListener);

				frame.add(splitPane, BorderLayout.CENTER);
				frame.setSize(300, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

}