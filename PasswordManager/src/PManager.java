import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.Random;

public class PManager {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/password_database"; 
		Properties props = new Properties();
		props.setProperty("user", "joeloseiasamoah");
		
		url = "jdbc:postgresql://localhost:5432/password_database?user=joeloseiasamoah";
        String master_password = "joeloseiasamoah20";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter password: ");
        String ans = input.nextLine();
        boolean flag = true;
        if (ans.equals(master_password)) {
        	 try {
                 Connection conn = DriverManager.getConnection(url);
                 System.out.println("Connected to PostgreSQL server");
                 System.out.println("Welcome to Password Manager\n");
                 while (flag) {
                	 System.out.println("What would you like to do?");
                     Menu.printOptions();
                     ans = input.nextLine();
                     switch(ans) {
                     	case "1":
                     		System.out.println("What company should we delete?");
                     		String comp1 = input.nextLine();
                     		PreparedStatement pst_delete = conn.prepareStatement("DELETE FROM password WHERE company = ?");
                     		pst_delete.setString(1, comp1);
                     		pst_delete.executeUpdate();
                     		pst_delete.close();
                     		System.out.printf("%s deleted from database\n", ans);
                     		System.out.println("Remaining companies and their passwords");
                     		PManager.printAllPasswords(conn);
                     		System.out.println("Would you want to leave?\n Type 'y' for yes and 'n' for no");
                     		String res1 = input.nextLine();
                     		if (res1.equals("y")) {
                     			flag = false;
                     		}
                     		break;
                     	case "2":
                     		System.out.println("What company password do you want to update?");
                     		String comp2 = input.nextLine();
                     		//System.out.println("What is the new password?");
                     		//String pw2 = input.nextLine();
                     		PreparedStatement pst_update = conn.prepareStatement("UPDATE password SET password = ? WHERE company = ?");
                     		pst_update.setString(1, autoGenerate(master_password, comp2));
                     		pst_update.setString(2, comp2);
                     		pst_update.executeUpdate();
                     		pst_update.close();
                     		System.out.println("Remaining companies and their passwords");
                     		printAllPasswords(conn);
                     		System.out.println("Would you want to leave?\n Type 'y' for yes and 'n' for no");
                     		String res2 = input.nextLine();
                     		if (res2.equals("y")) {
                     			flag = false;
                     		}
                     		break;
                     	case "3":
                     		System.out.print("Name of new company: \n");
                     		String comp3 = input.nextLine();
                     		//System.out.print("Password for new company: \n");
                     		//String pw3 = input.nextLine();
                     		PreparedStatement pst_insert = conn.prepareStatement("INSERT INTO password (company, password)"
                     				+ "VALUES(?, ?)");
                     		pst_insert.setString(1, comp3);
                     		pst_insert.setString(2, autoGenerate(master_password, comp3));
                     		pst_insert.executeUpdate();
                     		pst_insert.close();
                     		System.out.println("Remaining companies and their passwords");
                     		printAllPasswords(conn);
                     		System.out.println("Would you want to leave?\n Type 'y' for yes and 'n' for no");
                     		String res3 = input.nextLine();
                     		if (res3.equals("y")) {
                     			flag = false;
                     		}
                     		break;
                     	case "4":
                     		PManager.printAllPasswords(conn);
                     		System.out.println("Would you want to leave?\n Type 'y' for yes and 'n' for no");
                     		String res4 = input.nextLine();
                     		if (res4.equals("y")) {
                     			flag = false;
                     		}
                     		break;
                     }
                 }
                 conn.close();
                 input.close();
             } catch (SQLException e) {
                 System.out.println("Error in connecting to PostgreSQL server");
                 e.printStackTrace();
             }
        }
        else {
        	System.out.println("Wrong password!");
        	input.close();
        }
	}
	
	public static void printAllPasswords(Connection con) throws SQLException {
		Statement st = con.createStatement();
 		ResultSet rs = st.executeQuery("SELECT * FROM password");
 		while (rs.next()) {
 			System.out.println("Company: " + rs.getString(2) + " " + "password: " + rs.getString(3));
 		}
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
					sb.setCharAt(i, Character.forDigit(num, 10));
				}
				if (num > (int) sb.charAt(i) % 10) {
					int new_char = ((int) sb.charAt(i)) + rand.nextInt(10);
                    while ((58 <= new_char && new_char <= 64) || (91 <= new_char && new_char <= 96) || new_char > 122) {
                        new_char = ((int) sb.charAt(i)) + rand.nextInt(10);
                    }
					sb.setCharAt(i, (char) new_char);
				}
			}
            hash--;
		}
		
		return sb.toString();
	}
}
