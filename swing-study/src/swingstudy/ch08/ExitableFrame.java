package swingstudy.ch08;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ExitableFrame extends JFrame {

	public ExitableFrame() {}

	public ExitableFrame(String title) {
		super(title);
	}

	protected void frameInit() {
		super.frameInit();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}