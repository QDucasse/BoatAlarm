package server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import boat.Boat;
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

	// ==================
	// Constructors

	public CentralSystem(List<Subscriber> subscriberList, List<Boat> boatList,List<Administrator> administratorList) {
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
	@Override
	public List<Boat> createBoatList() {
		this.boatList = new ArrayList<Boat>();
		for (Subscriber s : this.subscriberList) {
			this.addBoat(s.getBoat());
		}
		return boatList;
	}
	
	@Override
	public void updateBoatList() {
		this.boatList = this.createBoatList();
	}
	
	@Override
	public void addSubscriber(Subscriber subscriber) {
		this.subscriberList.add(subscriber);
	}

	@Override
	public void deleteSubscriber(Subscriber subscriber) {
		this.subscriberList.remove(subscriber);
	}
	
	@Override
	public void addBoat(Boat boat) {
		this.boatList.add(boat);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyNewBoat(boat);
		}
	}

	@Override
	public List<Confidence> getConfidenceList() {
		return confidenceList;
	}

	@Override
	public void addConfidence(Confidence confidence) {
		this.confidenceList.add(confidence);
		
	}

	@Override
	public void deleteBoat(Boat boat) {
		this.boatList.remove(boat);
	}


	@Override
	public void addAdministrator(Administrator administrator) {
		this.administratorList.add(administrator);
		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) {
		this.administratorList.remove(administrator);
		
	}


}
