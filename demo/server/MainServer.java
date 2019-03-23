package server;

import boat.DummyBoatList;
import users.DummySubscriberList;

public class MainServer {
	// ==================
	// Instance Variables
	private static CentralSystem cs;

	// ==================
	// Constructors

	// ==================
	// Methods

	/**
	 * Javadoc comment template First: Quick description of the class/method
	 * 
	 * @param aParameter //To describe a parameter The parameter's description
	 * @return //To describe the result of the method
	 * @throws //To describe any thrown exception @see aMethodName //To link to
	 *         another method
	 */

	public static void main(String[] args) {
		DummyBoatList dummyBoat = new DummyBoatList();
		DummySubscriberList dummySubs = new DummySubscriberList();
		cs = new CentralSystem(dummySubs.getSubscriberList(),dummyBoat.getBoatList());
		System.out.println(cs.getSubscriberList());
		System.out.println(cs.getBoatList());

	}
}
