
public class Person {
	//==================
	//Instance Variables

	protected String name;
	protected String address;
	protected String email;
	
	//==================
	//Constructors
	public Person(String name, String address, String email)
	{
		this.setName(name);
		this.setName(address);
		this.setName(email);
	}
	
	//==================
	//Getters and Setters
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
	//==================
	//Other methods
	
	@Override
	public String toString() {
		return "Personne [name=" + name + ", address=" + address + ", email=" + email + "]";
	}
	
}
