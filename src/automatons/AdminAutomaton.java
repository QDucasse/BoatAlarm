package automatons;

import java.time.LocalDate;

import client.TCPClient;

public class AdminAutomaton implements IAutomaton {
	// ==================
	// Instance Variables
	
	private int port = 6669;
	private TCPClient tcpClient;
	int logged;
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
	
	public void addSubscriber(String account, String password, String name, String address, String email, 
							  LocalDate date,
							  String confMail, int confNum, 
							  int boatImmatriculation, String boatName, String boatType, String boatModel) {
		serverAnswer=tcpClient.sendString("add " + account + " " + password  + " " + address  + " " + email
										  + " " + date  
										  + " " + confMail  + " " + confNum  
										  + " " + boatImmatriculation + " " + boatName + " " + boatType + " " + boatModel);
	}
	
	public void deleteSubscriber(String account) {
		serverAnswer=tcpClient.sendString("delete " + account);
	}
	
}
