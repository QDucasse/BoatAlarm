package server;

import java.util.List;

import users.Subscriber;

public interface IContext {
	// ================
	// Abstract methods
	public List<Subscriber> getSubscriberList();

	public void addSubscriber(Subscriber subscriber);

	public void deleteSubscriber(Subscriber subscriber);

}
