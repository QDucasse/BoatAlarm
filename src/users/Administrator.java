package users;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Administrator extends User {
	//==================
	//Instance Variables
	
	private int choice;
	
	List<Subscriber> subscriberList;
	

	//==================
	//Constructors
	
	public Administrator(String person_name,String person_address,String person_email,
						 List<Subscriber> subscriberList,int choice)
	{
		super(person_name,person_address,person_email);
		this.subscriberList = subscriberList;
		this.choice = 0;
	}
	
	public Administrator(String person_name,String person_address,String person_email)
	{
		super(person_name,person_address,person_email);
		this.subscriberList = new ArrayList<Subscriber>();
		this.choice = 0;
	}
	
	public Administrator(String person_name)
	{
		super(person_name,"BoatAlarm Inc",(person_name+"@boat-alarm.com"));
		this.subscriberList = new ArrayList<Subscriber>();
		this.choice = 0;
	}
		
	//==================
	//Getters and Setters
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	}

	public Administrator(String person_name,String person_address, String person_email, int choice)
	{
		super(person_name,person_address,person_email);
		this.choice = choice;
		
	}

	//==================
	//Other methods
	public void simulation()
	{
		String person_name, person_address, person_email, boat_name, boat_type, boat_model;
		int boat_imm_number=0; 
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			System.out.println("\n\n********************************* ");
			System.out.println("************ MENU *************** ");
			System.out.println("\n1- Add a new subscriber");
			System.out.println("2- Remove a subscriber");
			System.out.println("3- Display subscriber list");
			System.out.println("0- Quit");
			System.out.println("\n Your Choice:  ");
			
			// saisir votre choix
			try {
				choice = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
			    e.printStackTrace();
			}
			
			switch(choice)
			{
				case 1 : // to do: ajouter un nouvelle abonnee
						System.out.println("\n Choice 1: ADD A SUBSCRIBER ");
						System.out.println("Name: \n");
						person_name = scan.nextLine();
						System.out.println("Address: \n");
						person_address = scan.nextLine();
						System.out.println("Email: \n");
						person_email = scan.nextLine();
						System.out.println("Boat immatriculation number: \n");
						try {
							boat_imm_number = Integer.parseInt(scan.nextLine());
						} catch (NumberFormatException e) {
						    e.printStackTrace();
						}
						//boat_imm_number = scan.nextInt();
						System.out.println("Boat name: \n");
						boat_name = scan.nextLine();
						System.out.println("Boat type: \n");
						boat_type = scan.nextLine();
						System.out.println("Boat model: \n");
						boat_model = scan.nextLine();
						
						Subscriber ab = new Subscriber(person_name, person_address, person_email, boat_imm_number, boat_name, boat_type, boat_model);
						//Ajoute ce nouvelle abonne a la liste des abonne
						subscriberList.add(ab);
						
					break;
					
				case 2 : // To do: supprimer un abonner en utilisant 
						//  son nom et son numero immatriculation de bateau
						System.out.println("\n Supprimer Un abonnee par son nom et son numero immatri bateaux : ");
						
						//Nom Personne
						System.out.println("Nom Personne: ");
						person_name = scan.nextLine();
						
						//Immatriculation de bateau
						System.out.println("Numero d\'immatriculation: ");
						try {
							boat_imm_number = Integer.parseInt(scan.nextLine());
						} catch (NumberFormatException e) {
						    e.printStackTrace();
						}
						// use iterator instead of foreach because a list cannot be modified while used in a foreach
						Iterator<Subscriber> iter = subscriberList.iterator();
						while (iter.hasNext()) {
							Subscriber abo=iter.next();
							if(abo.getName().equals(person_name) && abo.getBoat().getImmatriculation()==boat_imm_number)
							{
								iter.remove();
								System.out.println("Supression d\'abonne qui a le nom: "+person_name);
								subscriberList.remove(abo);
								System.out.println("abonne "+person_name+" est supprime");
							}
						}
					
					break;
					
				case 3 : // To do: affichage de la liste des abonnee
						//this.getAbonneListe();
						System.out.println("\n Choix 3: AFFICHAGE ");
						for (Subscriber a : subscriberList) {
							   System.out.println(a.toString());
							   System.out.println("\n");
							}
						
					break;
					
				case 0 : // TO do: quiter
						// close the scanner
						scan.close();
						System.exit(0);
					break;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "Administrateur [name=" + name + ", address=" + address + ", email=" + email + ", subscriberList=" + subscriberList + "]";
	}
	
}