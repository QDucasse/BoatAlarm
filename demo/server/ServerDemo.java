package server;

import boat.DummyBoatList;
import users.DummySubscriberList;
import users.DummyAdminList;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class ServerDemo {
	// ==================
	// Instance Variables
	private static CentralSystem cs;

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) {
		DummyBoatList dummyBoats = new DummyBoatList();
		DummySubscriberList dummySubs = new DummySubscriberList();
		DummyAdminList dummyAdmins = new DummyAdminList();
		cs = new CentralSystem(dummySubs.getSubscriberList(), dummyBoats.getBoatList(),
				dummyAdmins.getAdministratorList());
		System.out.println(cs.getSubscriberList());
		System.out.println(cs.getBoatList());
	}
}
