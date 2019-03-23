package server;

import java.util.List;

import users.Subscriber;
import boat.Boat;

public interface IContext {
	// ================
	// Abstract methods
	public List<Subscriber> getSubscriberList();
	
	public List<Boat> getBoatList();

	public void addSubscriber(Subscriber subscriber);

	public void deleteSubscriber(Subscriber subscriber);

}
