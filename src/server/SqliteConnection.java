package server;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 * <b>SQLiteConnection allows the connection to the database by the system</b>
 * <p>
 * A CentralSystem instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A connection to the database</li>
 * </ul>
 * 
 * @see external.SQLHandler
 * @author Mahmoud Souayfan and Georges Tanios
 */

public class SqliteConnection {
	// =============
	// Instance variables

	/**
	 * The connection to the database
	 */

	Connection conn = null;

	// ============
	// Constructors

	/**
	 * SQLiteConnection Constructor.
	 * <p>
	 * Through construction, the system is initialized and the connection is
	 * launched with the DriverManager
	 * </p>
	 * 
	 * @return A connection to the database
	 * @see CentralSystem
	 * @see CentralSystem#subscriberList
	 * @see CentralSystem#boatList
	 * @see CentralSystem#administratorList
	 * @see CentralSystem#TEST_PORT
	 * @see CentralSystem#USER_PORT
	 * @see CentralSystem#ADMIN_PORT
	 * @see CentralSystem#BOAT_PORT
	 */

	public static Connection dbConnector() {
		try {
			String dbname = "db.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:/Users/acc/Desktop/Ensta/2eme/java/projet-javaBoatAlarm/src/" + dbname);
			// .getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + dbname);

			// JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
