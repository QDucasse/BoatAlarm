package automatons;

import client.TCPClient;

public class UserAutomaton implements IAutomaton{
	//==================
	//Instance Variables

	private int port = 6668;
	private TCPClient tcpClient;
	int logged;
	
	//==================
	//Constructors
	
	public UserAutomaton() {
		this.tcpClient = new TCPClient("localhost",port);
		this.logged = 0;
	}
	
	//==================
	//Methods
	
	@Override
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	@Override
	public void deconnection() {
		this.tcpClient.disconnectFromServer();
		this.logged=0;
	}
	
	public void login(String account,String password) {
		tcpClient.sendString("login " + account +" "+ password);
		this.logged=1;
	}
	
	public void changeAccountName(String oldPassword, String newPassword) {
		tcpClient.sendString("changename " + oldPassword + " " + newPassword);
		//Verify ??
	}
	
	public void changePassword(String oldAccountName, String newAccountName) {
		tcpClient.sendString("changepassword " + oldAccountName + " " + newAccountName);
		//Verify ??
	}
	
	public String getBoatInfo(String boatName) {
		String boatInfo = tcpClient.sendString("getboatinfo " + boatName);
		return boatInfo;
	}

}
