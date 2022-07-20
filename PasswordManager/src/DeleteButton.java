

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DeleteButton implements ActionListener{
	JButton button;
	JTextField textField;
	JTextArea textArea;
	JButton dc;
	JPanel p;
	String mes;
	Connection connection;
	DeleteButton (JTextField tf, JTextArea ta, Connection con) {
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
 		String comp = textField.getText();
 		PreparedStatement pst_delete = connection.prepareStatement("DELETE FROM password WHERE company = ?");
 		pst_delete.setString(1, comp);
 		pst_delete.executeUpdate();
 		pst_delete.close();
 		textArea.setText(null);
 		textArea.append(comp + " deleted from database\n");
 		this.printAllPasswords(connection);
	}
	
	public void printAllPasswords(Connection con) throws SQLException {
		Statement st = con.createStatement();
 		ResultSet rs = st.executeQuery("SELECT * FROM password");
 		textArea.append("Remaining companies and their passwords\n");
 		while (rs.next()) {
			textArea.append("Company: " + rs.getString(2) + " || " + "Password: " + rs.getString(3) + "\n");
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
