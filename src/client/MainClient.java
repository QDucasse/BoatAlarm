package client;

import automatons.UserAutomaton;

public class MainClient {
	//==================
	//Instance Variables
	
	//==================
	//Constructors
	
	//==================
	//Methods
	
	public static void main(String[] args) {
		UserAutomaton usrTest = new UserAutomaton();
		usrTest.connection();
	}
}
