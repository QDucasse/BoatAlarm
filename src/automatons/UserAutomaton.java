package automatons;

import client.TCPClient;

public class UserAutomaton {
	//==================
	//Instance Variables

	private int port = 6668;
	private TCPClient tcpClient;
	private int logged;
	
	//==================
	//Constructors
	
	public UserAutomaton() {
		this.tcpClient = new TCPClient("localhost",port);
		this.logged = 0;
	}
	
	//==================
	//Methods
	
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	public void deconnection() {
		this.tcpClient.disconnectFromServer();
		this.logged=0;
	}
	
	public void login(String account,String password) {
		tcpClient.sendString("login " + account +" "+ password);
		//Verify ??
		this.logged=1;
	}
	
	public void changeAccountName(String account,String oldPassword, String newPassword) {
		tcpClient.sendString("changename " + account + " " + oldPassword + " " + newPassword);
		//Verify ??
	}
	
	public void changePassword(String oldAccountName,String password, String newAccountName) {
		tcpClient.sendString("changepassword " + oldAccountName + " " + password + " " + newAccountName);
		//Verify ??
	}
	
	public String getBoatInfo(String account,String password, String boatName) {
		String boatInfo = tcpClient.sendString("getboatinfo " + account + " " + password + " " + boatName);
		//Verify ??
		return boatInfo;
	}

}
