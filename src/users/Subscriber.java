package users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import boat.Boat;


public class Subscriber {
	//==================
	//Instance Variables
	private int id;
	private int BoatID;
	private int ConfidenceID;
	private String subscriberType;
	private List<String> emailList = new ArrayList<String>();
	private String account;
	private String password;
	private String name;
	private String address;
	private String email;
	private Date subscription_date;
	private List<Confidence> trusted_persons = new ArrayList<Confidence>();
	private Boat boat;

	
	List<Subscriber> subscriberList=new ArrayList<Subscriber>();
	//==================
	//Constructors
	
	public Subscriber(String account,String password,
					  String name, String address, String email, 
					  Date date, Confidence conf_person,
					  int boat_immatriculation, String boat_name, 
					  String boat_type, String boat_model)
	{
		
		// list_boat = centralSystem.getBoatListe();
		// prendre l'id le plus grand de la liste 
//		for (Boat b : list_boat) {
//			   id = b.getBoatId();
//			   }
//		
		// id = id + 1;
//this.BoatID = id;
		
		// la meme chose pour le Confidence
		// list_confidence = centralSystem.getListeConfidence();
		// prendre l'id le plus grand de la liste 
//		for (Confidence c : list_confidence) {
//			   id = c.getConfidenceID();
//			   }
//		
		// id = id + 1;
//this.CondidenceID = id;
//		
//*************************************************************************
// Il faut ajouter dans les constructeur dans Boat et dans Confidence le 
// truc des id pour les ajouter sur les objet .	
//**************************************************************************		
		
		this.account	       = account;
		this.password	       = password;
		this.name			   = name;
		this.address		   = address;
		this.trusted_persons.add(conf_person);
		this.email			   =email;
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
