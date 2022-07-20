import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	static String mpassword = "joel";
	public static void main(String[] args) {
		Launchpage lp = new Launchpage(); // creation of launchpage
		lp.getMPassword("joeloseiasamoah20");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        lp.getFrame(frame);
        frame.add(lp.returnPanel());
        frame.pack();
        frame.setVisible(true);
        
	}
}
