package clientServer;
public class MainClient {
	//==================
	//Instance Variables
	
	//==================
	//Constructors
	
	//==================
	//Methods
	
	public static void main(String[] args) {
		TCPClient myClient = new TCPClient("localhost", 6666);
		myClient.connectToServer();
	}
}
