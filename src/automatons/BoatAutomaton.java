package automatons;

import client.TCPClient;

public class BoatAutomaton implements IAutomaton {
	// ==================
	// Instance Variables

	private int port = 6667;
	private TCPClient tcpClient;
	private int alarm = 0;
	private int monitoring = 0;

	// ==================
	// Constructors

	public BoatAutomaton() {
		tcpClient = new TCPClient("localhost", port);
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

	public void monitor() {

	}
	
	public void triggerMonitoring(String accountName, String boatName) {
		this.tcpClient.sendString("triggerMonitor" + " " + boatName + " " + accountName);
	}

	public void triggerAlarm(String accountName, String boatName) {
		this.tcpClient.sendString("alarm" + " " + boatName + " " + accountName);
	}

	public void stopAlarm(String accountName, String boatName) {
		this.tcpClient.sendString("stopalarm" + " " + boatName + " " + accountName);
	}
}
