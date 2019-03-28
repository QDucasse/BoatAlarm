package boat;

import automatons.BoatAutomaton;
import java.util.concurrent.TimeUnit;

public class BoatDemo {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) throws InterruptedException {
		BoatAutomaton boatTest = new BoatAutomaton();
		boatTest.connection();
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(1,1));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(2,2));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(3,3));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(4,4));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(5,5));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(6,6));
		TimeUnit.SECONDS.sleep(1);
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(7,7));
	}


}
