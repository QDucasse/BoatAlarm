package client;

import automatons.UserAutomaton;

public class MainClient {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) {
		UserAutomaton usrTest = new UserAutomaton();
		usrTest.connection();
		usrTest.login("QDucasse", "1234");
		usrTest.changeAccountName("QDucasse", "ducassqu");
		usrTest.changePassword("1234", "1111");
	}
}
