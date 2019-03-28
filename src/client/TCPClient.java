package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	// ==================
	// Instance Variables

	private int port;

	private String serverName;

	private Socket serverSocket;

	private PrintStream socOut;

	private BufferedReader socIn;

	// ==================
	// Constructors

	/* A client connects to a server identified by a name and a port */
	public TCPClient(String serverName, int port) {
		this.port = port;
		this.serverName = serverName;
	}

	// ==================
	// Getters and Setters

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServerName() {
		return serverName;
	}

	public Socket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(Socket serverSocket) {
		this.serverSocket = serverSocket;
	}


	// ==================
	// Other Methods

	public boolean connectToServer() {
		boolean ok = false;
		try {
			System.out.println("Connection attempt: " + this.getServerName() + " -- " + this.getPort());
			this.setServerSocket(new Socket(this.getServerName(), this.getPort()));
			socOut = new PrintStream(this.getServerSocket().getOutputStream());
			socIn = new BufferedReader(new InputStreamReader(this.getServerSocket().getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Unknown server: " + e);

		} catch (ConnectException e) {
			System.err.println("Exception during connection:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception during data exchange:" + e);
		}
		System.out.println("Successful connection");
		return ok;
	}

	public void disconnectFromServer() {
		try {
			System.out.println("[TCPClient] Disconnection: " + this.getServerSocket());
			socOut.close();
			socIn.close();
			this.getServerSocket().close();
		} catch (Exception e) {
			System.err.println("Exception during disconnection: " + e);
		}
	}

	public String sendString(String message) {
		String serverMessage = null;
		try {
			System.out.println("Client request: " + message);
			socOut.println(message);
			socOut.flush();
			serverMessage = socIn.readLine();
			System.out.println("Server answer: " + serverMessage);

		} catch (UnknownHostException e) {
			System.err.println("Unknown server: " + e);
		} catch (IOException e) {
			System.err.println("I/O exception:  " + e);
			e.printStackTrace();
		}
		return serverMessage;
	}

}
