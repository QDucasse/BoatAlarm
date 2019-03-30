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
		String msg=this.tcpClient.sendString("logout");
		System.out.println(msg);
		this.tcpClient.disconnectFromServer();
	}

	public int login(String account, String password) {
		serverAnswer=tcpClient.sendString("login " + account + " " + password);
		String chaines[] = serverAnswer.split(" ");
		return Integer.parseInt(chaines[0]);
	}

	public void changeAccountName(String oldAccountName, String newAccountName) {
		serverAnswer=tcpClient.sendString("changename " + oldAccountName + " " + newAccountName);
	}

	public void changePassword(String accountName, String oldPassword, String newPassword) {
		serverAnswer=tcpClient.sendString("changepassword " + accountName  + " " + oldPassword + " " + newPassword);
	}

	public void getBoatInfo(String accountName, String boatName) {
		serverAnswer=tcpClient.sendString("getboatinfo " + accountName + " " + boatName);
	}
	
	public void monitor(String accountName,String boatName) {
		serverAnswer=tcpClient.sendString("monitor " + accountName + " " + boatName);
	}
	
	public String getBoatName(String accountName) {
		return tcpClient.sendString("getboatname " + accountName);
	}
	
	public String getBoatState(String accountName) {
		return tcpClient.sendString("getboatstate " + accountName);
	}

}
