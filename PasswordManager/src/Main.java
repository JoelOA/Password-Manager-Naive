import javax.swing.JFrame;

public class Main {
	static String mpassword = "joel";
	public static void main(String[] args) {
		Launchpage lp = new Launchpage();
		lp.setBounds(0, 0, 750, 375);
		lp.getMPassword("joeloseiasamoah20");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 375);
        
        lp.getFrame(frame);
        frame.add(lp.returnPanel());
        frame.setVisible(true);
        
	}
}
