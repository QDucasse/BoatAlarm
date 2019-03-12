package server;
import java.io.*;
import java.net.*;
import java.util.List;

import users.Subscriber;


public class TCPServer extends Thread{
	
	//==================
	//Instance Variables
	
	private static int connectionNumber = 0;
	
	private int maxConnect;
	
	private Socket clientSocket;	
	   
	private int portNumber;
	
	private List<Subscriber> subscriberList;
	
	private IContext context;
	
	private IProtocol protocol;
	
	//==================
	//Constructors
	
	public TCPServer(int unNumeroPort) {        
		portNumber = unNumeroPort;
		maxConnect = 10;
	} 

	//==================
	//Setters & Getters
	
	public static int getConnectionNumber() {
		return connectionNumber;
	}

	public static void setConnectionNumber(int connectionNumber) {
		TCPServer.connectionNumber = connectionNumber;
	}

	public int getMaxConnect() {
		return maxConnect;
	}

	public void setMaxConnect(int maxConnect) {
		this.maxConnect = maxConnect;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	} 
	
	public IContext getContext() {
		return context;
	} 
	
	public void setContext(IContext context) {
		this.context = context;
	} 
	
	public IProtocol getProtocol() {
		return protocol;
	} 
	
	public void setProtocol(IProtocol protocol) {
		this.protocol = protocol;
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

