

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateButton implements ActionListener{
	JButton button;
	JTextField textField;
	JTextArea textArea;
	JButton dc;
	JPanel p;
	String mes;
	String master_password = "joeloseiasamoah20";
	Connection connection;
	UpdateButton (JTextField tf, JTextArea ta, Connection conn) {
		button = new JButton();
		button = new JButton();
		button.setBackground(new Color(232, 241, 242));
		this.textField = tf;
		this.textArea = ta;
		this.connection = conn;
        button.addActionListener(this);
	}
	
	/*
	 * A method that is performed when this button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			try {
				this.doAction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*
	 * A method that sets the bounds of the button
	 * @param x a variable that represents the x coordinate of the button
	 * @param y a variable that represents the y coordinate of the button
	 * @param width a variable that represents the width of the button
	 * @param height a variable that represents the height of the button
	 */
	public void setBounds(int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
	}
	
	/*
	 * A method that receives the company user would like to update
	 * processes and executes this query by changing its password in the
	 * PostgreSQL database.
	 */
	public void doAction() throws SQLException {
 		String comp = textField.getText();
 		PreparedStatement pst_update = connection.prepareStatement("UPDATE password SET password = ? WHERE company = ?");
 		pst_update.setString(1, autoGenerate(master_password, comp)); // calls autoGenerate method to generate a new password
 		pst_update.setString(2, comp);
 		pst_update.executeUpdate();
 		pst_update.close();
 		textArea.append(comp + " password updated successfully\n");
 		this.printAllPasswords(connection);
	}
	
	/*
	 * A method that prints out all the companies and their 
	 * associated passwords
	 * @throws SQLException
	 */
	public void printAllPasswords(Connection con) throws SQLException {
		Statement st = con.createStatement();
 		ResultSet rs = st.executeQuery("SELECT * FROM password");
 		textArea.append("Here are your current passwords: \n");
 		while (rs.next()) {
			textArea.append("Company: " + rs.getString(2) + " || " + "password: " + rs.getString(3) + "\n");
 		}
 		textArea.append("-------------------------------------------------\n\n");
 		rs.close();
 		st.close();
	}
	
	/*
	 * A method that automatically creates a new password using 
	 * a hashing algorithm applied on the master password and 
	 * company.
	 * @param mpassword - a variable that represents the master password
	 * @param comp_name - a variable that represents the company name
	 */
	public static String autoGenerate(String mpassword, String comp_name) {
		String intermediate = mpassword + comp_name;
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < intermediate.length(); i++) {
			sb.append(intermediate.charAt(i));
		}
		int hash = 20;
		while (hash > 0) {
			for (int i = 0; i < sb.length(); i++) {
				int num = rand.nextInt(10);
				if (num < (int) sb.charAt(i) % 10) {
					int new_char = ((int) sb.charAt(i)) + rand.nextInt(10);
                    while ((58 <= new_char && new_char <= 64) || (91 <= new_char && new_char <= 96) || new_char > 122) {
                        new_char = ((int) sb.charAt(i)) + rand.nextInt(10);
                    }
					sb.setCharAt(i, (char) new_char);
				}
				if (num > (int) sb.charAt(i) % 10) {
					sb.setCharAt(i, Character.forDigit(num, 10));
				}
			}
            hash--;
		}
		
		return sb.substring(0, 15).toString();
	}
	
	/*
	 * A method that sets the text of the button
	 * @param mes - a variable that represents the text displayed on the button
	 */
	public void setText(String mes) {
		button.setText(mes);
	}
	
	/*
	 * A method that gets the panel this button would be placed on
	 * @param ascp - a variable that represents the panel the button would be placed on.
	 */
	public void getPanel(JPanel ascp) {
		p = ascp;
	}
	
	/*
	 * A method that returns the button created by this class
	 */
	public JButton getButton() {
		return button;
	}
}
