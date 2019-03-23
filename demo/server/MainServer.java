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
		cs = new CentralSystem(new DummyBoatList(), new DummySubscriberList());

	}
}
