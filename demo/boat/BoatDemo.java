package boat;

import automatons.BoatAutomaton;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class BoatDemo {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void movingTide(BoatAutomaton boatTest, String accountName, String boatName)  throws InterruptedException {
		for (int i=0;i<15; i++) {
			boatTest.transmitGPSData(accountName,boatName, new GPS(i, i));
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		BoatAutomaton boatTest = new BoatAutomaton();
		boatTest.connection();
		movingTide(boatTest,"QDucasse","LeGoëland");
		//LIGNE A DECOMMENTER POUR LA DEMO!!
		//movingTide(boatTest,"JChampeau","Brest");
	}

}
