package automatons;

import boat.GPS;
import client.TCPClient;

/**
 * <b>BoatAutomaton is the Automaton factoring and sending boat-related
 * instructions</b>
 * <p>
 * A BoatAutomaton instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A port on which it should connect: 6667</li>
 * <li>A ClientTCP providing server/client connection and communication</li>
 * <li>A serverAnswer collecting the answer of the server after sending the
 * instruction</li>
 * </ul>
 * 
 * @see IAutomaton
 * @author Quentin Ducasse
 */

public class BoatAutomaton implements IAutomaton {
	// ==================
	// Instance Variables

	/**
	 * The port through which the client will connect
	 */
	private int port = 6667;
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
	 * BoatAutomaton Constructor.
	 * <p>
	 * Through construction, the client is initialized with the given port
	 * </p>
	 * 
	 * @see BoatAutomaton
	 * @see BoatAutomaton#port
	 * @see BoatAutomaton#tcpClient
	 */

	public BoatAutomaton() {
		tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Methods

	/**
	 * Connects to the server through the given port
	 * 
	 * @return A boolean asserting if the connection is established
	 * @see BoatAutomaton
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
	 * Transmits GPS data to the central system Tests if the account owns the boat
	 * 
	 * @param accountName Name of the account.
	 * @param boatName    Name of the boat.
	 * @param gpsData     New position to transmit
	 */

	public void transmitGPSData(String accountName, String boatName, GPS gpsData) {
		serverAnswer = this.tcpClient.sendString(
				"gpsdata " + accountName + " " + boatName + " " + gpsData.getLat() + " " + gpsData.getLon());
	}
}
