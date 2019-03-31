package server;

import java.io.IOException;
import java.net.Socket;

/**
 * <b>TCPDedicatedThread handles every connection incoming to the main server
 * ports and treat their requests with the correct protocol</b>
 * <p>
 * A TCPDedicatedThread instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A client socket</li>
 * <li>The server it was run from</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class TCPDedicatedThread extends Thread {
	// ==================
	// Instance Variables

	/**
	 * The socket linked to the thread
	 */
	private Socket clientSocket;

	/**
	 * The server the thread was run from
	 */
	private TCPServer mainServer;

	// ==================
	// Constructors

	/**
	 * TCPDedicatedThread Constructor.
	 * <p>
	 * Through construction, the system is initialized with the given socket and
	 * server
	 * </p>
	 * 
	 * @param aSocket The server socket being listened
	 * @param aServer The TCP server listening
	 * @see TCPServer
	 * @see TCPDedicatedThread#clientSocket
	 * @see TCPDedicatedThread#mainServer
	 */

	public TCPDedicatedThread(Socket aSocket, TCPServer aServer) {

		super("ServeurThread");
		this.clientSocket = aSocket;
		this.mainServer = aServer;

	}

	// ==================
	// Other Methods

	/**
	 * Launches the correct protocol and links the inputs and outputs of the client
	 * and server
	 * 
	 * @see protocols.IProtocol
	 * @see IContext
	 */

	public void run() {
		try {
			mainServer.getProtocol().execute(mainServer.getContext(), clientSocket.getInputStream(),
					clientSocket.getOutputStream());
			System.out.println("Protocol executed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
