package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import protocols.IProtocol;
import users.Subscriber;

public class TCPServer extends Thread {

	// ==================
	// Instance Variables

	private static int CONNECTION_NB = 0;

	private int maxConnect;

	private Socket clientSocket;

	private int portNumber;

	private List<Subscriber> subscriberList;

	private IContext context;

	private IProtocol protocol;

	// ==================
	// Constructors

	public TCPServer(int port, int max) {
		this.portNumber = port;
		this.maxConnect = max;
	}

	public TCPServer(int port) {
		this(port, 10);
	}

	public TCPServer(IContext context, IProtocol protocol, int port) {
		this(port);
		this.context = context;
		this.protocol = protocol;
	}

	// ==================
	// Setters & Getters

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

	// ==================
	// Methods

	public String toString() {
		return "[ServeurTCP] Port : " + portNumber;
	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + portNumber + ", " + e);
			System.exit(1);
		}

		/* Currently authorizing maxConnect different client connections */
		while (CONNECTION_NB <= maxConnect) {
			try {
				System.out.println(" Waiting for connection on port " + portNumber + "...");
				clientSocket = serverSocket.accept();
				CONNECTION_NB++;
				System.out.println("Current connections: " + CONNECTION_NB);
			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
			TCPDedicatedThread st = new TCPDedicatedThread(clientSocket, this); 
			st.start(); 
		}
		System.out.println("Already " + CONNECTION_NB + " clients. Maximum authorised already reached");

		try {
			serverSocket.close();
			CONNECTION_NB--;
		} catch (IOException e) {
			System.out.println("Could not close socket: " + serverSocket.toString());
		}

	}

}
