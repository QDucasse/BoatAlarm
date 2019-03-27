package server;

import java.util.List;

import boat.Boat;
import users.Subscriber;
import users.Administrator;

public interface IContext {
	// ================
	// Abstract methods
	public List<Subscriber> getSubscriberList();
	
	public List<Boat> getBoatList();
	
	public List<Administrator> getAdministratorList();

	public void addSubscriber(Subscriber subscriber);

	public void deleteSubscriber(Subscriber subscriber);
	
	public void addBoat(Boat boat);

	public void deleteBoat(Boat boat);
	
	public void addAdministrator(Administrator administrator);

	public void deleteAdministrator(Administrator administrator);

}
