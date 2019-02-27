import java.io.*;
import java.net.*;


public class TCPServer extends Thread{
	
	//==================
	//Instance Variables
	
	private static int connectionNumber = 0;
	
	private int maxConnect;
	
	private Socket clientSocket;	
	   
	private int portNumber;
	
	//==================
	//Constructors
	
	public TCPServer(int unNumeroPort) {        
		portNumber = unNumeroPort;
		maxConnect = 10;
	} 

	//==================
	//Methods
	
	public String toString() {        
		return "[ServeurTCP] Port : " +  portNumber ;
	} 
	
	public void run() {        
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket ( portNumber );
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + portNumber + ", " + e);
			System.exit(1);
		}

		
		/* Currently authorizing maxConnect different client connections */
		while (connectionNumber <= maxConnect) {
			try {
				System.out.println(" Waiting for connection... " );
				clientSocket = serverSocket.accept();
				connectionNumber ++;
				System.out.println("Current connections: " + connectionNumber);
			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
        	/*ServeurSpecifique st = new ServeurSpecifique(clientSocket, this);*/
        	/*st.start();*/
		}
		System.out.println("Already " + connectionNumber + " clients. Maximum authorised already reached");

		try {
			serverSocket.close();
			connectionNumber --;
		} catch (IOException e) {
			System.out.println("Could not close socket: " + serverSocket.toString());
		}

	} 
	
		

}

