package boat;

import java.util.ArrayList;
import java.util.List;

public class DummyBoatList {
	// ==================
	// Instance Variables
	List<Boat> boatList = new ArrayList<Boat>();

	public DummyBoatList() {
		Boat boat1 = new Boat("48500", "LeGoëland", "Voilier", "Catamaran");
		Boat boat2 = new Boat("48501", "L'Agile", "Voilier", "Dériveur");
		Boat boat3 = new Boat("48502", "LeSolid", "Moteur", "Yacht");
		Boat boat4 = new Boat("48503", "L'ingénieurSecurité", "Moteur", "Yacht");
		
		boat1.monitor();
		
		boatList.add(boat1);
		boatList.add(boat2);
		boatList.add(boat3);
		boatList.add(boat4);
	}
	
	public List<Boat> getBoatList(){
		return boatList;
	}
}
