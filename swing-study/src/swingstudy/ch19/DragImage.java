package swingstudy.ch19;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
 
public class DragImage {
 
	public static void main(String[] args) {
 
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Drag Image");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
				Icon icon = new ImageIcon("image/dog.jpg");
				JLabel label = new JLabel(icon);
				label.setTransferHandler(new ImageSelection());
				MouseListener listener = new MouseAdapter() {
					public void mousePressed(MouseEvent event) {
						JComponent comp = (JComponent)event.getSource();
						TransferHandler handler = comp.getTransferHandler();
						handler.exportAsDrag(comp, event, TransferHandler.COPY);
					}
				};
				label.addMouseListener(listener);
				frame.add(new JScrollPane(label), BorderLayout.CENTER);
 
				frame.setSize(300, 150);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
 
}