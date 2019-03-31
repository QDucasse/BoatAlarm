package automatons;

import client.TCPClient;

/**
 * <b>TestAutomaton is the Automaton factoring and sending a simple message to
 * the server and test its answer</b>
 * <p>
 * An AdminAutomaton instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A port on which it should connect: 6666</li>
 * <li>A ClientTCP providing server/client connection and communication</li>
 * <li>A serverAnswer collecting the answer of the server after sending the
 * instruction</li>
 * </ul>
 * 
 * @see IAutomaton
 * @author Quentin Ducasse
 */

public class TestAutomaton implements IAutomaton {
	// ==================
	// Instance Variables

	/**
	 * The port through which the client will connect
	 */
	private int port = 6666;

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
	 * TestAutomaton Constructor.
	 * <p>
	 * Through construction, the client is initialized with the given port
	 * </p>
	 * 
	 * @see TestAutomaton
	 * @see TestAutomaton#port
	 * @see TestAutomaton#tcpClient
	 */

	public TestAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Methods

	/**
	 * Connects to the server through the given port
	 * 
	 * @see AdminAutomaton
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
	 * Sends a test message to the server, server answer: "Test passed!"
	 */

	public void testMessage() {
		serverAnswer = tcpClient.sendString("Test launched!");
		System.out.println(serverAnswer);
	}
}
