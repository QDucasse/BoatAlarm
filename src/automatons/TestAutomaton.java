package automatons;

import client.TCPClient;

public class TestAutomaton implements IAutomaton {
	// ==================
	// Instance Variables
	
	private int port = 6666;
	private TCPClient tcpClient;

	// ==================
	// Constructors

	public TestAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Methods

	@Override
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	@Override
	public void deconnection() {
		this.tcpClient.disconnectFromServer();
	}
	
	public void testMessage() {
		tcpClient.sendString("Test launched!");
	}
}
