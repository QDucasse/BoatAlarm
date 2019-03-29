package server;

import java.util.List;

import boat.Boat;
import users.Administrator;
import users.Confidence;
import users.Subscriber;

public interface IContext {
	// ================
	// Abstract methods
	
	public List<Subscriber> getSubscriberList();
	
	public List<Boat> getBoatList();
	
	public List<Administrator> getAdministratorList();
	
	public List<Confidence> getConfidenceList();
	
	public void addConfidence(Confidence confidence);

	public void addSubscriber(Subscriber subscriber);

	public void deleteSubscriber(Subscriber subscriber);
	
	public void addBoat(Boat boat);

	public void deleteBoat(Boat boat);
	
	public void addAdministrator(Administrator administrator);

	public void deleteAdministrator(Administrator administrator);
	
	public void createBoatList();
	
	public void updateBoatList();
	
	public void notifyLogin(Subscriber subscriber);
	
	public void notifyMonitoring(Subscriber subscriber);
	
	public void notifyAccountNameChange(String oldSub, String newSub);

}
