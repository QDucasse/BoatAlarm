package server;

import boat.Boat;
import users.Subscriber;

/**
 * <b>CentralSystemObserver the interface allowing the GUI to gather the model
 * data</b>
 * 
 * @see CentralSystem
 * @author Quentin Ducasse
 */

public interface CentralSystemObserver {

	/**
	 * Signature of the notification when a boat is added
	 * 
	 * @param boat The boat to add
	 */

	public void notifyAddBoat(Boat boat);

	/**
	 * Signature of the notification when a boat is deleted
	 * 
	 * @param boat The boat to delete
	 */

	public void notifyDeleteBoat(Boat boat);

	/**
	 * Signature of the notification when a subscriber is added
	 * 
	 * @param subscriber The subscriber to add
	 */

	public void notifyAddSubscriber(Subscriber subscriber);

	/**
	 * Signature of the notification when a subscriber is deleted
	 * 
	 * @param subscriber The subscriber to delete
	 */

	public void notifyDeleteSubscriber(Subscriber subscriber);

	/**
	 * Signature of the notification when a subscriber is logging in
	 * 
	 * @param subscriber The subscriber logging in
	 */

	public void notifyNewClientConnection(Subscriber subscriber);

	/**
	 * Signature of the notification when a boat is monitored
	 * 
	 * @param subscriber The subscriber whose boat is now monitoring
	 */

	public void notifyNewBoatMonitored(Subscriber subscriber);

	/**
	 * Signature of the notification when a boat is monitored
	 * 
	 * @param oldSubscriber The old subscriber's account name
	 * @param newSubscriber The new subscriber's account name
	 */

	public void notifyAccountNameChange(String oldSubscriber, String newSubscriber);

	/**
	 * Signature of the notification when a boat is monitored
	 * 
	 * @param boatName The boat whose state has changed
	 * @param state    The new state
	 */

	public void notifyStateChange(String boatName, String state);

}
