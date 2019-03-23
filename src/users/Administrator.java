package users;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
	// ==================
	// Instance Variables

	private int choice;

	List<Subscriber> subscriberList;

	// ==================
	// Constructors

	public Administrator(List<Subscriber> subscriberList, int choice) {
		this.subscriberList = subscriberList;
		this.choice = 0;
	}

	public Administrator() {
		this.subscriberList = new ArrayList<Subscriber>();
		this.choice = 0;
	}

	public Administrator(int choice) {
		this.choice = choice;

	}

	// ==================
	// Getters and Setters

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	@Override
	public String toString() {
		return "Administrateur [subscriberList=" + subscriberList + "]";
	}

}