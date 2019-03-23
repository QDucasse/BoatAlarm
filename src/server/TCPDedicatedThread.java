package server;

import java.io.IOException;
import java.net.Socket;

public class TCPDedicatedThread extends Thread {
	// ==================
	// Instance Variables
	private Socket clientSocket;
	private TCPServer mainServer;

	// ==================
	// Constructors

	public TCPDedicatedThread(Socket aSocket, TCPServer aServer) {

		super("ServeurThread");
		this.clientSocket = aSocket;
		this.mainServer = aServer;

	}

	// ==================
	// Other Methods

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
