package users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import boat.Boat;


public class Subscriber extends User {
	//==================
	//Instance Variables
	
	private Date subscription_date;
	//List<String> Emails = new ArrayList<String>();
	private List<User> trusted_persons = new ArrayList<User>();
	Boat boat;
	//==================
	//Constructors
	
	public Subscriber(String person_name, String person_address, String person_email, 
					  int boat_immatriculation, String boat_name, String boat_type, 
					  String boat_model)
	{
		super(person_name,person_address,person_email);
		this.subscription_date = new Date();
				// display time and date
			     // System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		this.boat = new Boat(boat_immatriculation, boat_name, boat_type,boat_model);
	}
	
	//==================
	//Getters and Setters

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
	}

	public List<User> getTrusted_persons() {
		return trusted_persons;
	}

	public void setTrusted_persons(List<User> trusted_persons) {
		this.trusted_persons = trusted_persons;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	/*
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	*/

	//==================
	//Other Methods
	
	@Override
	public String toString() {
		return "Abonne [name=" + name + ", address=" + address + ", email=" + email + ", subscription_date=" + subscription_date + ", boat=" + boat + "]";
	}


	
}
