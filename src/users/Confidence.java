package users;

public class Confidence {
	//==================
	//Instance Variables
	
	private String email;
	private int number;
	
	//==================
	//Constructors
	public Confidence(String e_mail, int num)
	{
		this.email=e_mail;
		this.number=num;

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
