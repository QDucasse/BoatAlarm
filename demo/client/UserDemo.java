package client;

import automatons.UserAutomaton;

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
		usrTest.login("QDucasse", "1234");
		usrTest.changeAccountName("QDucasse", "ducassqu");
		usrTest.changePassword("ducassqu","1234", "1111");
		usrTest.logout();
	}
}
