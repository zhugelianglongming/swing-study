package swingstudy.ch19;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
 
public class DragLabel {
 
	public static void main(String[] args) {
 
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Drag Label");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
				JLabel label = new JLabel("Hello, World");
				label.setTransferHandler(new TransferHandler("text"));
				MouseListener listener = new MouseAdapter() {
					public void mousePressed(MouseEvent event) {
						JComponent comp = (JComponent)event.getSource();
						TransferHandler handler = comp.getTransferHandler();
						handler.exportAsDrag(comp, event, TransferHandler.COPY);
					}
				};
 
				label.addMouseListener(listener);
				frame.add(label, BorderLayout.SOUTH);
 
				JTextField text = new JTextField();
				frame.add(text, BorderLayout.NORTH);
 
				frame.setSize(300, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
 
}