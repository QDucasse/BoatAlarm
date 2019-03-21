package client;

public class MainClient {
	//==================
	//Instance Variables
	
	//==================
	//Constructors
	
	//==================
	//Methods
	
	public static void main(String[] args) {
		TCPClient myClient = new TCPClient("localhost", 6668);
		myClient.connectToServer();
	}
}
