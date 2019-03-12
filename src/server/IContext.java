package server;
import users.Subscriber;

import java.util.List;

public interface IContext {
	//================
	//Abstract methods
	public List<Subscriber> getSubscriberList();
	public void addSubscriber(Subscriber subscriber);
	public void deleteSubscriber(Subscriber subscriber);
	
}
