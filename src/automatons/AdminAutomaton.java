package automatons;

import java.time.LocalDate;

import client.TCPClient;

/**
 * <b>AdminAutomaton is the Automaton factoring and sending
 * administrator-related instructions</b>
 * <p>
 * An AdminAutomaton instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A port on which it should connect: 6669</li>
 * <li>A ClientTCP providing server/client connection and communication</li>
 * <li>A serverAnswer collecting the answer of the server after sending the
 * instruction</li>
 * </ul>
 * 
 * @see IAutomaton
 * @author Quentin Ducasse
 */

public class AdminAutomaton implements IAutomaton {
	// ==================
	// Instance Variables
	/**
	 * The port through which the client will connect
	 */
	private int port = 6669;
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
	 * AdminAutomaton Constructor.
	 * <p>
	 * Through construction, the client is initialized with the given port
	 * </p>
	 * 
	 * @see AdminAutomaton
	 * @see AdminAutomaton#port
	 * @see AdminAutomaton#tcpClient
	 */

	public AdminAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Other Methods

	/**
	 * Connects to the server through the given port
	 * 
	 * @see AdminAutomaton
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
		this.tcpClient.disconnectFromServer();
	}

	/**
	 * Logs in the server with account name and password, server answer: "1 - Login
	 * successful!" if the account and password matched "0 - Login failed!" if not
	 * 
	 * @param account  Name of the account
	 * @param password Password of the account
	 */

	public void login(String account, String password) {
		serverAnswer = tcpClient.sendString("login_" + account + "_" + password);
	}

	/**
	 * Adds a subscriber to the central system with all provided information, server
	 * answer: "Subscriber added!"
	 * 
	 * @param account             The account name
	 * @param password            The account password
	 * @param name                The subscriber name
	 * @param address             The subscriber address
	 * @param email               The subscriber email
	 * @param date                The subscription date
	 * @param confMail            The trusted person email
	 * @param confNum             The trusted person phone number
	 * @param boatImmatriculation The boat immatriculation
	 * @param boatName            The boat name
	 * @param boatType            The boat type
	 * @param boatModel           The boat model
	 */

	public void addSubscriber(String account, String password, String name, String address, String email,
			LocalDate date, String confMail, String confNum, String boatImmatriculation, String boatName,
			String boatType, String boatModel) {
		serverAnswer = tcpClient.sendString("add_" + account + "_" + password + "_" + address + "_" + email + "_" + name
				+ "_" + date + "_" + confMail + "_" + confNum + "_" + boatImmatriculation + "_" + boatName + "_"
				+ boatType + "_" + boatModel);
	}

	/**
	 * Removes a subscriber from the central system, server answer: "Subscriber
	 * deleted!"
	 * 
	 * @param account Account name
	 */

	public void deleteSubscriber(String account) {
		serverAnswer = tcpClient.sendString("delete_" + account);
	}

}
