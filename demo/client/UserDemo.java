package client;

import automatons.UserAutomaton;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class UserDemo {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) {
		UserAutomaton usrTest = new UserAutomaton();
		usrTest.connection();
		usrTest.login("GLeBoucher", "1234");
		usrTest.changeAccountName("GLeBoucher", "leboucgu");
		usrTest.changePassword("leboucgu", "1234", "1111");
		usrTest.monitor("leboucgu", "L'Agile");
	}
}
