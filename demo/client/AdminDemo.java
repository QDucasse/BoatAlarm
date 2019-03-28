package client;

import java.time.LocalDate;

import automatons.AdminAutomaton;

public class AdminDemo {
	// ==================
	// Instance Variables

	// ==================
	// Constructors

	// ==================
	// Methods

	public static void main(String[] args) {
		AdminAutomaton adminTest = new AdminAutomaton();
		adminTest.connection();
		adminTest.login("Admin3","1234");
		adminTest.addSubscriber("JChampeau", "0000", "Joel Champeau", "2 Rue François Verny", "joel.champeau@email.fr", 
							  LocalDate.now(),
							  "conf.mail@email.fr","0000", 
							  "69000", "L'agile","Voilier","Catamaran");
		adminTest.addSubscriber("LLeRoux", "0000", "Luka Le Roux", "2 Rue François Verny", "luka.leroux@email.fr", 
							  LocalDate.now(),
							  "conf.mail@email.fr","0000", 
							  "69001", "Le très agile","Voilier","Catamaran");
		adminTest.deleteSubscriber("JChampeau");
		
	}
}
