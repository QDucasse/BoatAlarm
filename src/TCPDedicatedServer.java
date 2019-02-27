import java.io.*;
import java.net.Socket;

public class TCPDedicatedServer extends Thread{
	//==================
	//Instance Variables
	private Socket clientSocket;
	private TCPServer mainServer;	
	
	//==================
	//Constructors
	
    public  TCPDedicatedServer(Socket aSocket, TCPServer aServer) {
    	
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
        String inputReq;
/*        
        try {
        	BufferedReader is = new BufferedReader(new InputStreamReader(
        			clientSocket.getInputStream()));
        	PrintStream os = new PrintStream(clientSocket.getOutputStream());
        	System.out.println("Serveur avec  Client ");
        
        	if ((inputReq = is.readLine()) != null) {
        		System.out.println(" Msg 2 Recu " + inputReq);
        		String chaines[] = inputReq.split(" ");
        		System.out.println(" Ordre Recu " + chaines[0]);
        		if (chaines[0].contentEquals("retrait")) {
        			int valeur = (new Integer(chaines[1])).intValue();
        
        			System.out.println(" valeur demandee  " + valeur);
        
        			int valeurRetrait = monServeur.getContexte()
        					.demandeRetrait(valeur);
        
        			String valeurExpediee = "" + valeurRetrait;
        			System.out.println(" Retrait dans serveur "
        					+ valeurExpediee);
        
        			os.println(valeurExpediee);
        
        			System.out.println(monServeur);
        		}
        		if (chaines[0].contentEquals("depot")) {
        			int valeur = (new Integer(chaines[1])).intValue();
        
        			System.out.println(" valeur demandee  " + valeur);
        
        			int valeurDepot = monServeur.getContexte()
        					.demandeDepot(valeur);
        
        			String valeurExpediee = "" + valeurDepot;
        			System.out.println(" Depot dans serveur " + valeurExpediee);
        
        			os.println(valeurExpediee);
        
        			System.out.println(monServeur);
        		}
				if (chaines[0].contentEquals(automateClient.Historique.requeteHisto)) {
					String outputString = "Liste des operations :\n";
					for (String h : monServeur.getContexte().getHistoriqueOperations()){
						outputString = outputString + h + "\n" ;
					}
					System.out.println(" Liste a envoyer : \n " + outputString );
					os.println(outputString);
					os.flush();

				} else {
					os.println("Erreur de protocole..... \n" );						
				}
        	}
        	clientSocket.close();
        	os.close();
        	is.close();
        
        } catch (IOException e) {
        	e.printStackTrace();
   		}
   		*/
    }
        
}


    

