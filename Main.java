package paintClone;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	
	public Main() {
		Canvas canvas = new Canvas();
		Tools tools = new Tools();
		JPanel mainJP = new JPanel();
		mainJP.setLayout(null);
		mainJP.add(tools);
		mainJP.add(canvas);
		add(mainJP);
		setSize(800,800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}
