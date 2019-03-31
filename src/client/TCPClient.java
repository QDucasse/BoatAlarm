package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <b>TCPClient defines the client that will connect to the Server using the TCP
 * protocol</b>
 * <p>
 * A TCPClient instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A port to plug in</li>
 * <li>A serverName to refer to</li>
 * <li>A server socket</li>
 * <li>An output PrintStream to send data</li>
 * <li>An input BufferedReader to receive data</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class TCPClient {

	// ==================
	// Instance Variables
	/**
	 * The port to plug in
	 */
	private int port;
	/**
	 * The server name
	 */
	private String serverName;
	/**
	 * The server socket
	 */
	private Socket serverSocket;
	/**
	 * The output stream
	 */
	private PrintStream socOut;
	/**
	 * The input reader
	 */
	private BufferedReader socIn;

	// ==================
	// Constructors

	/**
	 * TCPClient Constructor.
	 * <p>
	 * Through construction, the TCPClient simply gathers a given port and server
	 * name
	 * </p>
	 * 
	 * @param serverName The name of the host
	 * @param port       The port the client will go through
	 * @see TCPClient
	 * @see TCPClient#port
	 * @see TCPClient#serverName
	 */

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

	/**
	 * Tries to connect to the server
	 * 
	 * @return true if connection established, false if not
	 */

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

	/**
	 * Disconnect from the server
	 */

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

	/**
	 * Sends a message through the output stream and reads the answer
	 * 
	 * @param message The message to pass through
	 * @return serverMessage The server answer
	 * @see TCPClient#socIn
	 * @see TCPClient#socOut
	 */
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
