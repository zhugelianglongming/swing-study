package swingstudy.ch11;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
 
public class JScrollPaneToTopAction implements ActionListener {
 
	JScrollPane scrollPane;
 
	public JScrollPaneToTopAction(JScrollPane scrollPane) {
		if(scrollPane == null) {
			throw new IllegalArgumentException("JScrollPaneToTopAction: null JScrollPane");
		}
		this.scrollPane = scrollPane;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMinimum());
		horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
	}
 
}