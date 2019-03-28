package boat;

import automatons.BoatAutomaton;

public class BoatDemo {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) {
		BoatAutomaton boatTest = new BoatAutomaton();
		boatTest.connection();
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(1,1));
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(2,2));
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(3,3));
		boatTest.transmitGPSData("GLeBoucher","L'Agile",new GPS(4,4));
	}


}
