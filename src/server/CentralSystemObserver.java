package server;

import boat.Boat;
import users.Subscriber;

public interface CentralSystemObserver {
	
	public void notifyAddBoat(Boat boat);
	
	public void notifyDeleteBoat(Boat boat);

	public void notifyAddSubscriber(Subscriber subscriber);
	
	public void notifyDeleteSubscriber(Subscriber subscriber);
	
	public void notifyNewClientConnection(Subscriber subscriber);

	public void notifyNewBoatMonitored(Subscriber subscriber);
	
	public void notifyAccountNameChange(String oldSubscriber,String newSubscriber);
	
	public void notifyStateChange(String boatName,String state);
	
}
