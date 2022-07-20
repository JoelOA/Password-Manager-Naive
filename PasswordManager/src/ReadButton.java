

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
	
	public void setBounds(int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
	}
	
	public void doAction() throws SQLException {
		textArea.setText(null);
		this.printAllPasswords(connection);
	}
	
	public void printAllPasswords(Connection con) throws SQLException {
		Statement st = con.createStatement();
 		ResultSet rs = st.executeQuery("SELECT * FROM password");
 		textArea.append("here are your passwords: \n");
 		while (rs.next()) {
			textArea.append("Company: " + rs.getString(2) + " || " + "password: " + rs.getString(3) + "\n");
 		}
 		textArea.append("-------------------------------------------------\n");
 		rs.close();
 		st.close();
	}
	
	public void setText(String mes) {
		button.setText(mes);
	}
	public void getPanel(JPanel ascp) {
		p = ascp;
	}
	
	public JButton getButton() {
		return button;
	}
}
