
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		/*
		 * Creation of launch page with its preferred size
		 */
		launch = new JPanel();
		launch.setBackground(new Color(36, 123, 160));
		launch.setPreferredSize(new Dimension(750, 375));
		launch.setLayout(null);
		
		/*
		 * Creation of textField
		 */
		text_field = new JTextField();
		text_field.setBounds(100, 138, 550, 50);
		
		/*
		 * Creation of submit button
		 */
		submit_button = new JButton();
		submit_button.setBounds(275, 188, 200, 40);
		submit_button.setBackground(new Color(232, 241, 242));
		submit_button.setText("SUBMIT");
		submit_button.addActionListener(this);
		
		/*
		 * Creation of label for launch page
		 */
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
	
/*
 * This method is called when the submit_button is clicked
 * It checks if the password entered in the text field matches
 * the correct password. if it does it repaints the frame to 
 * introduce the main page
 */
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
				frame.add(mp.returnPanel1(), BorderLayout.EAST);
				frame.add(mp.returnPanel2(), BorderLayout.WEST);
				frame.pack();
			}
		}
	}
	
	/*
	 * This method is called in the main.java file to pass the 
	 * JFrame to launch page for it to be repainted in the 
	 * actionPerformed() method
	 * @param fr - JFrame of the program
	 */
	public void getFrame(JFrame fr) {
		frame = fr;
	}
	
	/*
	 * Takes the master password and assigns it to mpass
	 * @param mp master password
	 */
	public void getMPassword(String mp) {
		mpass = mp;
	}
	
	/*
	 * returns the launch page panel
	 * This method is called in main.java
	 */
	
	public JPanel returnPanel() {
		return launch;
	}
}
