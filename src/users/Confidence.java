package users;

public class Confidence {
	//==================
	//Instance Variables
	//
	private int confidenceID;
	private String email;
	private int number;
	
	//==================
	//Constructors
	public Confidence(String e_mail, int num)
	{
		
		this.email=e_mail;
		this.number=num;
		// dans le constructeur il faut prendre l'id le plus grands de la liste des 
		//  confidence ce qui est normalement la dernier dans la liste et ajouter l'id +1 pour le new confidence.

	}
	
	//==================
	//Setters & Getters
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	//==================
	//Methods
	@Override
	public String toString() {
		return "Confidence [email=" + email + ", number=" + number + "]";
	}
	
	
}
