package users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import boat.Boat;


public class Subscriber {
	//==================
	//Instance Variables
	
	private String account;
	private String password;
	private String name;
	private String address;
	private String email;
	private Date subscription_date;
	private List<String> emails = new ArrayList<String>();
	private List<Confidence> trusted_persons = new ArrayList<Confidence>();
	private Boat boat;

	
	//==================
	//Constructors
	
	public Subscriber(String account,String password,
					  String name, String address, String email, 
					  Date date, Confidence conf_person,
					  int boat_immatriculation, String boat_name, 
					  String boat_type, String boat_model)
	{
		this.account	       = account;
		this.password	       = password;
		this.name			   = name;
		this.address		   = address;
		this.trusted_persons.add(conf_person);
		this.emails.add(email);
		//this.subscription_date = date;
		this.subscription_date = new Date();
				// display time and date
			     // System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		this.boat = new Boat(boat_immatriculation, boat_name, boat_type,boat_model);
	}
	
	//==================
	//Getters and Setters

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<Confidence> getTrusted_persons() {
		return trusted_persons;
	}

	public void setTrusted_persons(List<Confidence> trusted_persons) {
		this.trusted_persons = trusted_persons;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	//==================
	//Other Methods
	
	@Override
	public String toString() {
		return "Abonne [name=" + name + ", address=" + address + ", email=" + email + ", subscription_date=" + subscription_date + ", boat=" + boat + "]";
	}



	
}
