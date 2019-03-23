package server;

import java.util.ArrayList;
import java.util.List;

import boat.Boat;
import protocols.AdminProtocol;
import protocols.StateProtocol;
import protocols.TestProtocol;
import protocols.UserProtocol;
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

	// ==================
	// Constructors

	public CentralSystem(List<Subscriber> subscriberList, List<Boat> boatList) {
		this.subscriberList = subscriberList;
		this.boatList = boatList;
		servers.add(new TCPServer(this, new TestProtocol(), TEST_PORT));
		servers.add(new TCPServer(this, new UserProtocol(), USER_PORT));
		servers.add(new TCPServer(this, new AdminProtocol(), ADMIN_PORT));
		servers.add(new TCPServer(this, new StateProtocol(), BOAT_PORT));
		for (TCPServer s : servers) {
			s.start();
		}
	}

	// ==================
	// Getters & Setters

	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	}

	// ==================
	// Methods

	public void addSubscriber(Subscriber subscriber) {
		this.subscriberList.add(subscriber);
	}

	public void deleteSubscriber(Subscriber subscriber) {
		this.subscriberList.remove(subscriber);
	}

}
