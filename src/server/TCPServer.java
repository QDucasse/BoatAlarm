package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import protocols.IProtocol;

/**
 * <b>TCPServer is the main part of the system, receiving incoming connections,
 * holding the data and answering to requests</b>
 * <p>
 * A CentralSystem instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A number of current connections</li>
 * <li>A number of maximum connections</li>
 * <li>A client socket</li>
 * <li>A port number</li>
 * <li>A context</li>
 * <li>A protocol</li>
 * </ul>
 * 
 * @see IContext
 * @author Quentin Ducasse
 */

public class TCPServer extends Thread {

	// ==================
	// Instance Variables
	/**
	 * Current number of connections
	 */
	private static int CONNECTION_NB = 0;
	/**
	 * Maximum number of connections
	 */
	private int maxConnect;
	/**
	 * Client socket the connection comes through
	 */
	private Socket clientSocket;
	/**
	 * Port number where the server listens
	 */
	private int portNumber;
	/**
	 * The central system interface
	 */
	private IContext context;
	/**
	 * The protocol interface
	 */
	private IProtocol protocol;

	// ==================
	// Constructors

	/**
	 * TCPServer Constructor.
	 * <p>
	 * Through construction, the system is initialized with the port, a max number
	 * of connections and a protocol
	 * </p>
	 * 
	 * @param port The port number
	 * @param max  The maximum number of connections
	 * @see CentralSystem
	 */

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

	public IContext getContext() {
		return context;
	}

	public IProtocol getProtocol() {
		return protocol;
	}

	// ==================
	// Methods

	/**
	 * Runs the server on the given port and accepts incoming connection. Each port
	 * is linked to a protocol and therefore the server launches a
	 * TCPDedicatedThread alongside the context and the correct protocol to execute
	 * any requests from the clients
	 */

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
