package server;
import java.io.*;
import java.net.Socket;

public class TCPDedicatedThread extends Thread{
	//==================
	//Instance Variables
	private Socket clientSocket;
	private TCPServer mainServer;	
	
	//==================
	//Constructors
	
    public  TCPDedicatedThread(Socket aSocket, TCPServer aServer) {
    	
        super("ServeurThread");
        this.clientSocket = aSocket;
        this.mainServer = aServer;

    }

    //==================
  	//Getters and Setters
    
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public TCPServer getMainServer() {
		return mainServer;
	}
 
	public void setMainServer(TCPServer mainServer) {
		this.mainServer = mainServer;
	}
    
	//==================
	//Other Methods
    
    public void run() {
    	try {
    		mainServer.getProtocol().execute(mainServer.getContext(), 
    										 clientSocket.getInputStream(), 
    										 clientSocket.getOutputStream());
			System.out.println("Protocol executed");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
        
}


    

