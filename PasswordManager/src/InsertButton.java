

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

public class InsertButton implements ActionListener{
	JButton button;
	JTextField textField;
	JTextArea textArea;
	JButton dc;
	JPanel p;
	String mes;
	String master_password = "joeloseiasamoah20";
	Connection connection;
	InsertButton (JTextField tf, JTextArea ta, Connection con) {
		button = new JButton();
		button = new JButton();
		//button.setText("DELETE");
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
		String comp = textField.getText();
		PreparedStatement pst_insert = connection.prepareStatement("INSERT INTO password (company, password)"
 				+ "VALUES(?, ?)");
 		pst_insert.setString(1, comp);
 		pst_insert.setString(2, autoGenerate(master_password, comp));
 		pst_insert.executeUpdate();
 		pst_insert.close();
 		textArea.setText(null);
 		textArea.append(comp + " has been added to your password database\n");
 		this.printAllPasswords(connection);
	}
	
	public void printAllPasswords(Connection con) throws SQLException {
		Statement st = con.createStatement();
 		ResultSet rs = st.executeQuery("SELECT * FROM password");
 		textArea.append("Here are your current passwords\n");
 		while (rs.next()) {
			textArea.append("Company: " + rs.getString(2) + " || " + "password: " + rs.getString(3) + "\n");
 		}
 		textArea.append("-------------------------------------------------\n");
 		rs.close();
 		st.close();
	}
	
	public static String autoGenerate(String mpassword, String user) {
		String intermediate = mpassword + user;
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
