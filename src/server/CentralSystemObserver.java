package server;

import boat.Boat;

public interface CentralSystemObserver {
	
	public void notifyNewBoat(Boat boat);
	
}
