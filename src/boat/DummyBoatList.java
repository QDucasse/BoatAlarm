package boat;

import java.util.ArrayList;
import java.util.List;

public class DummyBoatList extends ArrayList{
	//==================
	//Instance Variables
	List<Boat> boatList = new ArrayList<Boat>();
	
	public DummyBoatList() {
		Boat boat1 = new Boat(48500,"Le Goëland","Voilier", "Catamaran");
		Boat boat2 = new Boat(48501,"L'Agile","Voilier", "Dériveur");
		Boat boat3 = new Boat(48502,"Le Solid","Moteur", "Yacht");
		Boat boat4 = new Boat(48503,"L'ingénieur Securité","Moteur", "Yacht");
		
		boatList.add(boat1);
		boatList.add(boat2);
		boatList.add(boat3);
		boatList.add(boat4);
		}
}

