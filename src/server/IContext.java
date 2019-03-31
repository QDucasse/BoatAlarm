package server;

import java.util.List;

import boat.Boat;
import users.Administrator;
import users.Confidence;
import users.Subscriber;

/**
 * <b>IContext is the interface allowing the Central System to transmit his data
 * alongside some server utilities</b>
 * 
 * @see CentralSystem
 * @author Quentin Ducasse
 */

public interface IContext {

	// Subscriber list

	public List<Subscriber> getSubscriberList();

	public void addSubscriber(Subscriber subscriber);

	public void deleteSubscriber(Subscriber subscriber);

	// Boat list

	public List<Boat> getBoatList();

	public void addBoat(Boat boat);

	public void deleteBoat(Boat boat);

	public void createBoatList();

	// Confidence list

	public List<Confidence> getConfidenceList();

	public void addConfidence(Confidence confidence);

	// Administrator list

	public List<Administrator> getAdministratorList();

	public void addAdministrator(Administrator administrator);

	public void deleteAdministrator(Administrator administrator);

	// Notifications

	/**
	 * Signature of the notification when a subscriber is logging in
	 * 
	 * @param subscriber The subscriber logging in
	 */

	public void notifyLogin(Subscriber subscriber);

	/**
	 * Signature of the notification when a boat is monitored
	 * 
	 * @param subscriber The subscriber whose boat is now monitoring
	 */

	public void notifyMonitoring(Subscriber subscriber);

	/**
	 * Signature of the notification when a user changes his account name
	 * 
	 * @param oldSub The old subscriber's account name
	 * @param newSub The new subscriber's account name
	 */

	public void notifyAccountNameChange(String oldSub, String newSub);

	/**
	 * Signature of the notification when a boat is monitored
	 * 
	 * @param boatName The boat whose state has changed
	 * @param state    The new state
	 */

	public void notifyStateChange(String boatName, String state);

}
