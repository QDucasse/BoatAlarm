package users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import boat.Boat;

public class Subscriber {
	// ==================
	// Instance Variables
	private int subId;
	private static int subCounter = 0;
	private String subscriberType;
	private List<String> emailList = new ArrayList<String>();
	private String account;
	private String password;
	private String name;
	private String address;
	private String email;
	private LocalDate subscriptionDate;
	private List<Confidence> trustedPersons = new ArrayList<Confidence>();
	private Boat boat;

	List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	// ==================
	// Constructors

	public Subscriber(String account, String password, String name, String address, String email, LocalDate date,
			String conf_mail, String conf_num, String boat_immatriculation, String boat_name, String boat_type, String boat_model) {

		this.subId = subCounter;
		this.subscriberType = "subscriber";
		this.account = account;
		this.password = password;
		this.name = name;
		this.address = address;
		this.trustedPersons.add(new Confidence(conf_mail,conf_num));
		this.email = email;
		// this.subscription_date = date;
		this.subscriptionDate = LocalDate.now();
		// display time and date
		// System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		this.boat = new Boat(boat_immatriculation, boat_name, boat_type, boat_model);
		subCounter++;
	}

	// ==================
	// Getters and Setters


	public String getSubscriberType() {
		return subscriberType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}
	
	public String getAddress() {
		return address;
	}

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


	public void addTrustedPerson(Confidence trusted_person) {
		trustedPersons.add(trusted_person);
	}

	public List<Confidence> getTrustedPersons() {
		return trustedPersons;
	}
	
	public Boat getBoat() {
		return boat;
	}
	
	public int getSubId() {
		return subId;
	}


	// ==================
	// Other Methods

	public List<String> createEmailList() {
		emailList.add(this.email);
		for (Confidence c : this.trustedPersons) {
			emailList.add(c.getEmail());
		}
		return emailList;
	}

	@Override
	public String toString() {
		return "Abonne [name=" + name + ", address=" + address + ", email=" + email + ", subscription_date="
				+ subscriptionDate + ", boat=" + boat + "]";
	}

}
