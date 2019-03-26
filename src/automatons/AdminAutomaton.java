package automatons;

import java.time.LocalDate;

import client.TCPClient;

public class AdminAutomaton implements IAutomaton {
	// ==================
	// Instance Variables
	
	private int port = 6669;
	private TCPClient tcpClient;
	String serverAnswer;
	
	// ==================
	// Constructors

	public AdminAutomaton() {
		this.tcpClient = new TCPClient("localhost", port);
	}
	
	// ==================
	// Other Methods
	
	@Override
	public boolean connection() {
		return tcpClient.connectToServer();
	}

	@Override
	public void deconnection() {
		this.tcpClient.disconnectFromServer();
	}
	
	public void login(String account, String password) {
		serverAnswer=tcpClient.sendString("login_" + account + "_" + password);
		//logged?
	}
	
	public void addSubscriber(String account, String password, String name, String address, String email, 
							  LocalDate date,
							  String confMail, String confNum, 
							  String boatImmatriculation, String boatName, String boatType, String boatModel) {
		serverAnswer=tcpClient.sendString("add_" + account + "_" + password  + "_" + address  + "_" + email
										  + "_" + date  
										  + "_" + confMail  + "_" + confNum  
										  + "_" + boatImmatriculation + "_" + boatName + "_" + boatType + "_" + boatModel);
	}
	
	public void deleteSubscriber(String account) {
		serverAnswer=tcpClient.sendString("delete_" + account);
	}
	
}
