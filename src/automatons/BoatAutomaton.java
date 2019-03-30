package automatons;

import boat.GPS;
import client.TCPClient;

public class BoatAutomaton implements IAutomaton {
	// ==================
	// Instance Variables

	private int port = 6667;
	private TCPClient tcpClient;

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
	public void logout() {
		this.tcpClient.disconnectFromServer();
	}

	public void transmitGPSData(String accountName, String boatName, GPS gpsData) {
		this.tcpClient.sendString("gpsdata " + accountName + " " + boatName + " " + gpsData.getLat() + " " + gpsData.getLon());
	}
/*	
	public void triggerMonitoring(String accountName, String boatName) {
		this.tcpClient.sendString("triggerMonitor" + " " + boatName + " " + accountName);
	}

	public void triggerAlarm(String accountName, String boatName) {
		this.tcpClient.sendString("alarm" + " " + boatName + " " + accountName);
	}

	public void stopAlarm(String accountName, String boatName) {
		this.tcpClient.sendString("stopalarm" + " " + boatName + " " + accountName);
	}
*/
}
