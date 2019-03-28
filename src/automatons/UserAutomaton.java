package automatons;

import client.TCPClient;

public class UserAutomaton implements IAutomaton {
	// ==================
	// Instance Variables

	private int port = 6668;
	private TCPClient tcpClient;
	String serverAnswer;
	
	// ==================
	// Constructors
	
	public UserAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}

	// ==================
	// Methods

	@Override
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	@Override
	public void logout() {
		this.tcpClient.sendString("logout");
		this.tcpClient.disconnectFromServer();
	}

	public void login(String account, String password) {
		serverAnswer=tcpClient.sendString("login " + account + " " + password);
	}

	public void changeAccountName(String oldAccountName, String newAccountName) {
		serverAnswer=tcpClient.sendString("changename " + oldAccountName + " " + newAccountName);
	}

	public void changePassword(String accountName, String oldPassword, String newPassword) {
		serverAnswer=tcpClient.sendString("changepassword " + accountName  + " " + oldPassword + " " + newPassword);
	}

	public void getBoatInfo(String boatName) {
		serverAnswer=tcpClient.sendString("getboatinfo " + boatName);
	}

}
