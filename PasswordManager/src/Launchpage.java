
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Launchpage extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	String url = "jdbc:postgresql://localhost:5432/password_database"; 
	Properties props = new Properties();
	Connection conn;
	
	JPanel launch;
	JFrame frame;
	JTextField text_field;
	JButton submit_button;
	JLabel wlcm;
	String mpass = "";
	Launchpage () {
		props.setProperty("user", "joeloseiasamoah");
		url = "jdbc:postgresql://localhost:5432/password_database?user=joeloseiasamoah";
		
		launch = new JPanel();
		launch.setBackground(new Color(36, 123, 160));
		launch.setLayout(null);
		
		text_field = new JTextField();
		text_field.setBounds(100, 138, 550, 50);
		
		submit_button = new JButton();
		submit_button.setBounds(275, 188, 200, 40);
		submit_button.setBackground(new Color(232, 241, 242));
		submit_button.setText("SUBMIT");
		submit_button.addActionListener(this);
		
		wlcm = new JLabel();
		wlcm.setText("WELCOME TO PASSWORD MANAGER");
		wlcm.setBackground(new Color(232, 241, 242));
		wlcm.setFont(new Font("Raleway", Font.BOLD, 25));
		wlcm.setBounds(100, 88, 550, 50);
		wlcm.setHorizontalAlignment(JLabel.CENTER);
		wlcm.setOpaque(true);
		
		launch.add(text_field);
		launch.add(submit_button);
		launch.add(wlcm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit_button) {
			if (mpass.equals(text_field.getText())) {
				try {
					conn = DriverManager.getConnection(url);
	                System.out.println("Connected to PostgreSQL server");
				}
				catch (SQLException e1) {
					System.out.println("Error in connecting to PostgreSQL server");
	                 e1.printStackTrace();
				}
                
				frame.remove(launch);
				frame.revalidate();
				Mainpage mp = new Mainpage(conn, frame);
				frame.add(mp.returnPanel1());
				frame.add(mp.returnPanel2());
			}
		}
	}
	
	public void setBounds(int x, int y, int width, int height) {
		launch.setBounds(x, y, width, height);
	}
	
	public void getFrame(JFrame fr) {
		frame = fr;
	}
	
	public void getMPassword(String mp) {
		mpass = mp;
	}
	
	public JPanel returnPanel() {
		return launch;
	}
}
