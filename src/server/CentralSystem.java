package server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import boat.Boat;
import external.SQLHandler;
import protocols.AdminProtocol;
import protocols.BoatProtocol;
import protocols.TestProtocol;
import protocols.UserProtocol;
import users.Administrator;
import users.Confidence;
import users.Subscriber;


public class CentralSystem implements IContext {
	// ==================
	// Instance Variables

	private static final int TEST_PORT = 6666;
	private static final int BOAT_PORT = 6667;
	private static final int USER_PORT = 6668;
	private static final int ADMIN_PORT = 6669;
	public List<TCPServer> servers = new ArrayList<TCPServer>();
	List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	List<Boat> boatList = new ArrayList<Boat>();
	List<Confidence> confidenceList = new ArrayList<Confidence>();
	List<Administrator> administratorList = new ArrayList<Administrator>();
	private final Set<CentralSystemObserver> observerSet;
	private SQLHandler sqlHandler;

	// ==================
	// Constructors

	public CentralSystem(List<Subscriber> subscriberList, List<Boat> boatList,List<Administrator> administratorList) {
		sqlHandler = new SQLHandler(this);
		//TODO Read database
		this.subscriberList = subscriberList;
		this.boatList = boatList;
		this.administratorList = administratorList;
		observerSet = new HashSet<>();
		servers.add(new TCPServer(this, new TestProtocol(), TEST_PORT));
		servers.add(new TCPServer(this, new UserProtocol(), USER_PORT));
		servers.add(new TCPServer(this, new AdminProtocol(), ADMIN_PORT));
		servers.add(new TCPServer(this, new BoatProtocol(), BOAT_PORT));
		for (TCPServer s : servers) {
			s.start();
		}
	}
	
	public CentralSystem(List<Subscriber> subscriberList,List<Administrator> administratorList) {
		this(subscriberList, new ArrayList<Boat>(),administratorList);
		this.createBoatList();
		
	}

	// ==================
	// Getters & Setters

	public void addObserver(CentralSystemObserver obs) {
		observerSet.add(obs);
	}
	
	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public List<Boat> getBoatList() {
		return boatList;
	}
	
	public List<Administrator> getAdministratorList() {
		return administratorList;
	}
	
	// ==================
	// Methods
	
	// List Gestion
	
	public void initializeLists() {
		//Trigger notifications
		List<Subscriber> dataSubs = this.subscriberList;
		this.subscriberList = new ArrayList<Subscriber>();
		this.boatList = new ArrayList<Boat>();
		
		for (Subscriber s : dataSubs) {
			this.addSubscriber(s);
		}
		
	}
	
	
	@Override
	public void createBoatList() {
		this.boatList = new ArrayList<Boat>();
		for (Subscriber s : this.subscriberList) {
			addBoat(s.getBoat());
		}
	}
	
	@Override
	public void updateBoatList() {
		for (Boat b : boatList) {
			deleteBoat(b);
		}
		this.createBoatList();
	}
	
	//SubscriberList
	
	@Override
	public void addSubscriber(Subscriber subscriber) {
		this.subscriberList.add(subscriber);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAddSubscriber(subscriber);
		}
	}

	@Override
	public void deleteSubscriber(Subscriber subscriber) {
		this.subscriberList.remove(subscriber);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyDeleteSubscriber(subscriber);
		}
	}
	
	//Boat List
	
	@Override
	public void addBoat(Boat boat) {
		this.boatList.add(boat);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAddBoat(boat);
		}
	}
	
	@Override
	public void deleteBoat(Boat boat) {
		this.boatList.remove(boat);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyDeleteBoat(boat);
		}
	}

	// ConfidenceList
	
	@Override
	public List<Confidence> getConfidenceList() {
		return confidenceList;
	}

	@Override
	public void addConfidence(Confidence confidence) {
		this.confidenceList.add(confidence);
		
	}
	
	// AdministratorList

	@Override
	public void addAdministrator(Administrator administrator) {
		this.administratorList.add(administrator);
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) {
		this.administratorList.remove(administrator);
		
	}
	
	// Notifications

	public void notifyLogin(Subscriber subscriber) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyNewClientConnection(subscriber);
		}
	}
	
	public void notifyMonitoring(Subscriber subscriber) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyNewBoatMonitored(subscriber);
		}
	}
	
	public void notifyAccountNameChange(String oldSub, String newSub) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAccountNameChange(oldSub,newSub);
		}
	}

}
