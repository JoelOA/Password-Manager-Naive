

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReadButton implements ActionListener{
	JButton button;
	JTextField textField;
	JTextArea textArea;
	JButton dc;
	JPanel p;
	String mes;
	
	Connection connection;
	ReadButton (JTextField tf, JTextArea ta, Connection con) {
		button = new JButton();
		button = new JButton();
		button.setBackground(new Color(232, 241, 242));
		this.textField = tf;
		this.textArea = ta;
		this.connection = con;
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
	 * A method that calls the printAllPasswords() method 
	 * when this button is clicked.
	 */
	public void doAction() throws SQLException {
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
 		textArea.append("here are your passwords: \n");
 		while (rs.next()) {
			textArea.append("Company: " + rs.getString(2) + " || " + "password: " + rs.getString(3) + "\n");
 		}
 		textArea.append("-------------------------------------------------\n\n");
 		rs.close();
 		st.close();
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
