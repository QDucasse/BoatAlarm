package users;

public class Administrator {
	// ==================
	// Instance Variables

	private String accountName;
	private String password;
	
	// ==================
	// Constructors

	public Administrator(String accountName, String password) {
		this.accountName = accountName;
		this.password = password;
	}
	// ==================
	// Getters & Setters
	
	public String getAccountName() {
		return accountName;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	// ==================
	// Other methods
	
	public int isAdmin() {
		return 1;
	}
	
	
	
}