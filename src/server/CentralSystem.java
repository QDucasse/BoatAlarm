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

/**
 * <b>CentralSystem is the main part of the system, receiving incoming
 * connections, holding the data and answering to requests</b>
 * <p>
 * A CentralSystem instance is characterized by the following :
 * </p>
 * <ul>
 * <li>4 final port values for: Test, Boat, User and Admin connections</li>
 * <li>A list of TCPServers</li>
 * <li>A list of administrators</li>
 * <li>A list of subscribers</li>
 * <li>A list of boats</li>
 * <li>A list of trusted persons</li>
 * <li>A set of observers</li>
 * <li>An SQLHandler</li>
 * </ul>
 * 
 * @see IContext
 * @author Quentin Ducasse
 */

public class CentralSystem implements IContext {
	// ==================
	// Instance Variables
	/**
	 * The port through which the test will occur
	 */
	private static final int TEST_PORT = 6666;
	/**
	 * The port through which the boats will transmit data
	 */
	private static final int BOAT_PORT = 6667;
	/**
	 * The port through which the users will interact
	 */
	private static final int USER_PORT = 6668;
	/**
	 * The port through which the administrators will interact
	 */
	private static final int ADMIN_PORT = 6669;
	/**
	 * The list of running TCPServers
	 */
	public List<TCPServer> servers = new ArrayList<TCPServer>();
	/**
	 * The list of all subscribers
	 */
	List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	/**
	 * The list of all boats
	 */
	List<Boat> boatList = new ArrayList<Boat>();
	/**
	 * The list of all trusted persons
	 */
	List<Confidence> confidenceList = new ArrayList<Confidence>();
	/**
	 * The list of all administrators
	 */
	List<Administrator> administratorList = new ArrayList<Administrator>();
	/**
	 * The set of all observers
	 */
	private final Set<CentralSystemObserver> observerSet;
	/**
	 * The sql handler
	 */
	private SQLHandler sqlHandler;

	// ==================
	// Constructors

	/**
	 * CentralSystem Constructor.
	 * <p>
	 * Through construction, the system is initialized with the given lists and
	 * starts the 4 servers
	 * </p>
	 * 
	 * @param subscriberList    The list of subscriber
	 * @param boatList          The list of boats
	 * @param administratorList The list of administrators
	 * @see CentralSystem
	 * @see CentralSystem#subscriberList
	 * @see CentralSystem#boatList
	 * @see CentralSystem#administratorList
	 * @see CentralSystem#TEST_PORT
	 * @see CentralSystem#USER_PORT
	 * @see CentralSystem#ADMIN_PORT
	 * @see CentralSystem#BOAT_PORT
	 */

	public CentralSystem(List<Subscriber> subscriberList, List<Boat> boatList, List<Administrator> administratorList) {
		sqlHandler = new SQLHandler(this);
		// Database not working
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

	public CentralSystem(List<Subscriber> subscriberList, List<Administrator> administratorList) {
		this(subscriberList, new ArrayList<Boat>(), administratorList);
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

	public SQLHandler getSQLHandler() {
		return sqlHandler;
	}

	// ==================
	// Methods

	// List Gestion

	/**
	 * Builds the subscriber list by adding each subscriber to trigger notifications
	 * to the observers
	 * 
	 * @see CentralSystemObserver
	 * @see CentralSystem#observerSet
	 */

	public void initializeLists() {
		// Trigger notifications
		List<Subscriber> dataSubs = this.subscriberList;
		this.subscriberList = new ArrayList<Subscriber>();
		this.boatList = new ArrayList<Boat>();

		for (Subscriber s : dataSubs) {
			this.addSubscriber(s);
		}

	}

	/**
	 * Builds the boat list from the subscriber list
	 * 
	 * @see CentralSystem#boatList
	 * @see CentralSystem#subscriberList
	 */

	@Override
	public void createBoatList() {
		this.boatList = new ArrayList<Boat>();
		for (Subscriber s : this.subscriberList) {
			addBoat(s.getBoat());
		}
	}

	// SubscriberList

	/**
	 * Adds a subscriber to the list and notify the observers
	 * 
	 * @param subscriber The subscriber to add
	 * @see CentralSystem#observerSet
	 * @see CentralSystem#subscriberList
	 * @see CentralSystemObserver#notifyAddSubscriber(Subscriber)
	 */

	@Override
	public void addSubscriber(Subscriber subscriber) {
		this.subscriberList.add(subscriber);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAddSubscriber(subscriber);
		}
	}

	/**
	 * Deletes a subscriber from the list and notify the observers
	 * 
	 * @param subscriber The subscriber to delete
	 * @see CentralSystem#observerSet
	 * @see CentralSystem#subscriberList
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyDeleteSubscriber(Subscriber)
	 */

	@Override
	public void deleteSubscriber(Subscriber subscriber) {
		this.subscriberList.remove(subscriber);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyDeleteSubscriber(subscriber);
		}
	}

	// Boat List
	/**
	 * Adds a boat to the list and notify the observers
	 * 
	 * @param boat The boat to add
	 * @see CentralSystem#observerSet
	 * @see CentralSystem#boatList
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyAddBoat(Boat)
	 */

	@Override
	public void addBoat(Boat boat) {
		this.boatList.add(boat);
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAddBoat(boat);
		}
	}

	/**
	 * Deletes a boat from the list and notify the observers
	 * 
	 * @param boat The boat to delete
	 * @see CentralSystem#observerSet
	 * @see CentralSystem#boatList
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyDeleteBoat(Boat)
	 */

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

	/**
	 * Adds a trusted person to the list
	 * 
	 * @param confidence The trusted person to add
	 * @see CentralSystem#confidenceList
	 */

	@Override
	public void addConfidence(Confidence confidence) {
		this.confidenceList.add(confidence);

	}

	// AdministratorList

	/**
	 * Adds an administrator to the list
	 * 
	 * @param administrator The administrator to add
	 * @see CentralSystem#administratorList
	 */

	@Override
	public void addAdministrator(Administrator administrator) {
		this.administratorList.add(administrator);

	}

	/**
	 * Deletes an administrator from the list
	 * 
	 * @param administrator The administrator to delete
	 * @see CentralSystem#administratorList
	 */

	@Override
	public void deleteAdministrator(Administrator administrator) {
		this.administratorList.remove(administrator);

	}

	// Notifications

	/**
	 * Notifies a login to the observers
	 * 
	 * @param subscriber The subscriber that logged in
	 * @see CentralSystem#observerSet
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyNewClientConnection(Subscriber)
	 */

	@Override
	public void notifyLogin(Subscriber subscriber) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyNewClientConnection(subscriber);
		}
	}

	/**
	 * Notifies a boat monitoring to the observers
	 * 
	 * @param subscriber The subscriber that triggered the monitoring on his boat
	 * @see CentralSystem#observerSet
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyNewBoatMonitored(Subscriber)
	 */

	@Override
	public void notifyMonitoring(Subscriber subscriber) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyNewBoatMonitored(subscriber);
		}
	}

	/**
	 * Notifies a user changing its account name to the observers
	 * 
	 * @param oldSub The old name
	 * @param newSub The new name
	 * @see CentralSystem#observerSet
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyAccountNameChange(String, String)
	 */

	@Override
	public void notifyAccountNameChange(String oldSub, String newSub) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyAccountNameChange(oldSub, newSub);
		}
	}

	/**
	 * Notifies a boat changing state to the observers
	 * 
	 * @param boatName The name of the boat that changed state
	 * @param state    The new state
	 * @see CentralSystem#observerSet
	 * @see CentralSystemObserver
	 * @see CentralSystemObserver#notifyStateChange(String, String)
	 */

	@Override
	public void notifyStateChange(String boatName, String state) {
		for (CentralSystemObserver obs : observerSet) {
			obs.notifyStateChange(boatName, state);
		}

	}

}
