package users;

/**
 * <b>Confidence is the data and behavior of a trusted person in our model</b>
 * <p>
 * A Confidence instance is characterized by the following :
 * </p>
 * <ul>
 * <li>An id (for the database)</li>
 * <li>An email address</li>
 * <li>A phone number</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class Confidence {
	// ==================
	// Instance Variables
	//
	private static int confidenceCounter = 0;
	/**
	 * The id of the trusted person
	 */
	private int confidenceId;
	/**
	 * The email address of the trusted person
	 */
	private String email;
	/**
	 * The phone number of the trusted person
	 */
	private String number;

	// ==================
	// Constructors
	/**
	 * Confidence constructor
	 * <p>
	 * The constructor simply initializes the Confidence with a given email and
	 * phone number
	 * </p>
	 * 
	 * @param e_mail The email of the trusted person
	 * @param num    The phone number of the trusted person
	 */
	public Confidence(String e_mail, String num) {
		this.confidenceId = confidenceCounter;
		this.email = e_mail;
		this.number = num;
		confidenceCounter++;
	}

	// ==================
	// Setters & Getters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getConfidenceId() {
		return confidenceId;
	}

	// ==================
	// Methods
	@Override
	public String toString() {
		return "Confidence [email=" + email + ", number=" + number + "]";
	}

}
