

import java.awt.Color;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Mainpage {
	JPanel pb_panel;
	JPanel sb_panel;
	JTextField tf;
	JTextArea ta;
	
	Mainpage (Connection con) {
		ta = new JTextArea();
		ta.setBounds(50, 50, 400, 190);
		
		tf = new JTextField();
		tf.setBounds(100, 250, 300, 50);
		
		DeleteButton delete_button = new DeleteButton(tf, ta, con);
		delete_button.setBounds(50, 35, 150, 50);
		delete_button.setText("DELETE");
		
		UpdateButton update_button = new UpdateButton(tf, ta, con);
		update_button.setBounds(50, 120, 150, 50);
		update_button.setText("UPDATE");
		
		InsertButton insert_button = new InsertButton(tf, ta, con);
		insert_button.setBounds(50, 205, 150, 50);
		insert_button.setText("INSERT");
		
		ReadButton read_button = new ReadButton(tf, ta, con);
		read_button.setBounds(50, 290, 150, 50);
		read_button.setText("READ");
		
		pb_panel = new JPanel();
		pb_panel.setBackground(new Color(19, 41, 61));
		pb_panel.setBounds(500, 0, 250, 375);
		pb_panel.setLayout(null);
		
		sb_panel = new JPanel();
		sb_panel.setBackground(new Color(0, 100, 148));
		sb_panel.setBounds(0, 0, 500, 375);
		sb_panel.setLayout(null);
		
		delete_button.getPanel(sb_panel);
		update_button.getPanel(sb_panel);
		insert_button.getPanel(sb_panel);
		read_button.getPanel(sb_panel);
		
		pb_panel.add(delete_button.getButton());
        pb_panel.add(update_button.getButton());
        pb_panel.add(insert_button.getButton());
        pb_panel.add(read_button.getButton());
        sb_panel.add(tf);
        sb_panel.add(ta);
	}
	
	public JPanel returnPanel1() {
		return pb_panel;
	}
	
	public JPanel returnPanel2() {
		return sb_panel;
	}
	
}
