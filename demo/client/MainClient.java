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
		usrTest.login("ducassqu", "1234");
		usrTest.changeAccountName("ducassqu", "quducass");
		usrTest.changePassword("1234", "1111");
	}
}
