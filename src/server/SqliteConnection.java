package server;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	// =============
	// Instance variables
	Connection conn = null;

	// ============
	// Constructors	
	public static Connection dbConnector() {
		try {
			String dbname = "db.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:/Users/acc/Desktop/Ensta/2eme/java/projet-javaBoatAlarm/src/" + dbname);
					//.getConnection("jdbc:sqlite:" +  System.getProperty("user.dir") + dbname);
			
			// JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
 