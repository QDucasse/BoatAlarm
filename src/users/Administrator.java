package users;

/**
 * <b>Administrator is the data and behavior of an administrator in our
 * model</b>
 * <p>
 * An Administrator instance is characterized by the following :
 * </p>
 * <ul>
 * <li>An account name</li>
 * <li>A password</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class Administrator {
	// ==================
	// Instance Variables
	/**
	 * The account name of the administrator
	 */
	private String accountName;
	/**
	 * The password of the account
	 */
	private String password;

	// ==================
	// Constructors

	/**
	 * 
	 * Administrator constructor
	 * <p>
	 * The constructor simply initializes the Administrator with a given account
	 * name and password
	 * </p>
	 * 
	 * @param accountName The name of the account
	 * @param password    The password of the account
	 */

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