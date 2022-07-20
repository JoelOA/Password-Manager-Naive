

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Mainpage {
	JPanel side_panel;
	JPanel main_panel;
	JTextField tf;
	JTextArea ta;
	
	Mainpage (Connection con, JFrame fr) {
		ta = new JTextArea(11, 30);
		
		JScrollPane scroll = new JScrollPane(ta);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tf = new JTextField();
		tf.setBounds(100, 250, 300, 50);
		
		// Creation of delete button
		DeleteButton delete_button = new DeleteButton(tf, ta, con);
		delete_button.setBounds(50, 35, 150, 50);
		delete_button.setText("DELETE");
		
		// Creation of update button
		UpdateButton update_button = new UpdateButton(tf, ta, con);
		update_button.setBounds(50, 120, 150, 50);
		update_button.setText("UPDATE");
		
		// Creation of insert button
		InsertButton insert_button = new InsertButton(tf, ta, con);
		insert_button.setBounds(50, 205, 150, 50);
		insert_button.setText("INSERT");
		
		// Creation of read button
		ReadButton read_button = new ReadButton(tf, ta, con);
		read_button.setBounds(50, 290, 150, 50);
		read_button.setText("READ");
		
		// creation of side panel to contain button
		side_panel = new JPanel();
		side_panel.setBackground(new Color(19, 41, 61));
		side_panel.setPreferredSize(new Dimension(250, 375));
		side_panel.setLayout(null);
		
		// Creation of main panel to contain text area and text field
		main_panel = new JPanel();
		main_panel.setBackground(new Color(0, 100, 148));
		main_panel.setPreferredSize(new Dimension(500, 375));
		main_panel.setLayout(new BorderLayout());
		
		delete_button.getPanel(main_panel);
		update_button.getPanel(main_panel);
		insert_button.getPanel(main_panel);
		read_button.getPanel(main_panel);
		
		side_panel.add(delete_button.getButton());
        side_panel.add(update_button.getButton());
        side_panel.add(insert_button.getButton());
        side_panel.add(read_button.getButton());
        main_panel.add(tf, BorderLayout.SOUTH);
        main_panel.add(scroll, BorderLayout.CENTER);
	}
	
	public JPanel returnPanel1() {
		return side_panel;
	}
	
	public JPanel returnPanel2() {
		return main_panel;
	}
	
}
