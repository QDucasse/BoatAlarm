package automatons;

import client.TCPClient;

/**
 * <b>UserAutomaton is the Automaton factoring and sending user-related
 * instructions</b>
 * <p>
 * An UserAutomaton instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A port on which it should connect: 6668</li>
 * <li>A ClientTCP providing server/client connection and communication</li>
 * <li>A serverAnswer collecting the answer of the server after sending the
 * instruction</li>
 * </ul>
 * 
 * @see IAutomaton
 * @author Quentin Ducasse
 */

public class UserAutomaton implements IAutomaton {
	// ==================
	// Instance Variables
	/**
	 * The port through which the client will connect
	 */
	private int port = 6668;
	/**
	 * The client itself
	 */
	private TCPClient tcpClient;
	/**
	 * The receiver of the server answers
	 */
	String serverAnswer;

	// ==================
	// Constructors

	/**
	 * UserAutomaton Constructor.
	 * <p>
	 * Through construction, the client is initialized with the given port
	 * </p>
	 * 
	 * @see UserAutomaton
	 * @see UserAutomaton#port
	 * @see UserAutomaton#tcpClient
	 */

	public UserAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Methods

	/**
	 * Connects to the server through the given port
	 * 
	 * @see UserAutomaton
	 * @return Confirmation of the connection
	 */

	@Override
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	/**
	 * Disconnects from the server
	 */

	@Override
	public void logout() {
		String msg = this.tcpClient.sendString("logout");
		System.out.println(msg);
		this.tcpClient.disconnectFromServer();
	}

	/**
	 * Logs in the server with account name and password, serverAnswer: "1 - Login
	 * successful!" if the account and password matched "0 - Login failed!" if not
	 * 
	 * @param account  Name of the account
	 * @param password Password of the account
	 * @return Confirmation of login
	 * 
	 */
	public int login(String account, String password) {
		serverAnswer = tcpClient.sendString("login " + account + " " + password);
		String chaines[] = serverAnswer.split(" ");
		return Integer.parseInt(chaines[0]);
	}

	/**
	 * Changes the account name (if logged in), server answer: "Name changed!" or
	 * "You need to login first"
	 * 
	 * @param oldAccountName Old name of the account
	 * @param newAccountName New name of the account
	 */

	public void changeAccountName(String oldAccountName, String newAccountName) {
		serverAnswer = tcpClient.sendString("changename " + oldAccountName + " " + newAccountName);
	}

	/**
	 * Changes the password (if logged in), server answer: "Password changed!" or
	 * "You need to login first"
	 * 
	 * @param accountName Name of the account
	 * @param oldPassword Old password of the account
	 * @param newPassword New password of the account
	 */

	public void changePassword(String accountName, String oldPassword, String newPassword) {
		serverAnswer = tcpClient.sendString("changepassword " + accountName + " " + oldPassword + " " + newPassword);
	}

	/**
	 * Get the information of the user's boat (if logged in), serverAnswer: All of
	 * the boat information or "You need to login first"
	 * 
	 * @param accountName Name of the account
	 * @param boatName    Name of the boat
	 */
	public void getBoatInfo(String accountName, String boatName) {
		serverAnswer = tcpClient.sendString("getboatinfo " + accountName + " " + boatName);
	}

	/**
	 * Trigger monitoring on the user's boat (if logged in), server answer: "Your
	 * boat is now monitored" or "You need to login first" or "You do not own a boat
	 * with that name"
	 * 
	 * @param accountName Name of the account
	 * @param boatName    Name of the boat
	 */
	public void monitor(String accountName, String boatName) {
		serverAnswer = tcpClient.sendString("monitor " + accountName + " " + boatName);
	}

	/**
	 * Get the boat's name (if logged in)
	 * 
	 * @param accountName Name of the account
	 * @return serverAnswer: Name of the boat
	 */
	public String getBoatName(String accountName) {
		return tcpClient.sendString("getboatname " + accountName);
	}

	/**
	 * Get the boat's state (if logged in)
	 * 
	 * @param accountName Name of the account
	 * @return serverAnswer: State of the boat
	 */
	public String getBoatState(String accountName) {
		return tcpClient.sendString("getboatstate " + accountName);
	}

}
