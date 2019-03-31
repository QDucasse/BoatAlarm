package external;

import boat.DummyBoatList;
import server.CentralSystem;
import users.DummyAdminList;
import users.DummySubscriberList;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class SQLDemo {

	private static CentralSystem cs;
	private static SQLHandler sqlhandler;

	public static void main(String[] args) {
		DummyBoatList dummyBoats = new DummyBoatList();
		DummySubscriberList dummySubs = new DummySubscriberList();
		DummyAdminList dummyAdmins = new DummyAdminList();
		cs = new CentralSystem(dummySubs.getSubscriberList(), dummyBoats.getBoatList(),
				dummyAdmins.getAdministratorList());
		sqlhandler = new SQLHandler(cs);
		sqlhandler.extractData();
		// Not working
	}
}
