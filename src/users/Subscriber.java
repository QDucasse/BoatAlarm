package users;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import boat.Boat;


public class Subscriber {
	//==================
	//Instance Variables
	private int subId;
	private static int subCounter = 0;
	private String subscriberType;
	private List<String> emailList = new ArrayList<String>();
	private String account;
	private String password;
	private String name;
	private String address;
	private String email;
	private LocalDate subscription_date;
	private List<Confidence> trusted_persons = new ArrayList<Confidence>();
	private Boat boat;

	
	List<Subscriber> subscriberList=new ArrayList<Subscriber>();
	//==================
	//Constructors
	
	public Subscriber(String account,String password,
					  String name, String address, String email, 
					  LocalDate date, Confidence conf_person,
					  int boat_immatriculation, String boat_name, 
					  String boat_type, String boat_model)
	{		
		
		this.subId = subCounter;
		this.subscriberType = "subscriber";
		this.account	       = account;
		this.password	       = password;
		this.name			   = name;
		this.address		   = address;
		this.trusted_persons.add(conf_person);
		this.email			   =email;
		//this.subscription_date = date;
		this.subscription_date = LocalDate.now();
				// display time and date
			     // System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);
		this.boat = new Boat(boat_immatriculation, boat_name, boat_type,boat_model);
		subCounter++;
	}
	
	//==================
	//Getters and Setters
	
	public int getSubId() {
		return subId;
	}
	
	public String getSubscriberType() {
		return subscriberType;
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

	public void addTrusted_person(Confidence trusted_person) {
		trusted_persons.add(trusted_person);
	}

	public List<Confidence> getTrusted_persons() {
		return trusted_persons;
	}

	public Boat getBoat() {
		return boat;
	}

	//==================
	//Other Methods
	
	public List<String> createEmailList() {
		emailList.add(this.email);
		for (Confidence c : this.trusted_persons) {
			emailList.add(c.getEmail());
		}
		return emailList;
	}
	
	
	@Override
	public String toString() {
		return "Abonne [name=" + name + ", address=" + address + ", email=" + email + ", subscription_date=" + subscription_date + ", boat=" + boat + "]";
	}



	
}
