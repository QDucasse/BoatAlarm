package boat;

import external.LogWriter;

/**
 * <b>Boat is the data and behavior of a boat in our model</b>
 * <p>
 * A Boat instance is characterized by the following :
 * </p>
 * <ul>
 * <li>An id (used in the database)</li>
 * <li>An immatriculation number</li>
 * <li>A name</li>
 * <li>A type</li>
 * <li>A model</li>
 * <li>A position</li>
 * <li>A place (used only if we extend the actual system to a port/pier with
 * different funcionning modes)</li>
 * <li>A state</li>
 * <li>A security zone that the boat should be in to be considered safe</li>
 * <li>A log writer to track data in a .txt file</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class Boat {

	// ==================
	// Instance Variables

	private static int boatCounter = 0;
	private int boatId;
	private String immatriculation;
	private String name;
	private String type;
	private String model;
	private String position;
	private String place;
	private String state;
	private SecurityZone boatSecuZone = new SecurityZone(new GPS(0, 0), 5);
	private LogWriter logWriter;

	// ==================
	// Constructors

	/**
	 * Boat Constructor.
	 * <p>
	 * Through construction, the Boat is initialized with position 0,0 and state
	 * "not monitoring"
	 * </p>
	 * 
	 * @param immatriculation The immatriculation of the boa
	 * @param name            The name of the boat
	 * @param type            The type of the boat (voilier, moteur,etc.)
	 * @param model           The model of the boat (Catamaran, Dériveur, Yacht,
	 *                        etc.)
	 * 
	 * @see Boat
	 * @see Boat#immatriculation
	 * @see Boat#name
	 * @see Boat#type
	 * @see Boat#model
	 */

	public Boat(String immatriculation, String name, String type, String model) {
		this(immatriculation, name, type, model, "0,0", "port", "not monitoring");
	}

	public Boat(String immatriculation, String name, String type, String model, String position, String place,
			String state) {
		this.boatId = boatCounter;
		this.immatriculation = immatriculation;
		this.name = name;
		this.type = type;
		this.model = model;
		this.position = position;
		this.place = place;
		this.state = state;
		this.logWriter = new LogWriter(this.name);
		boatCounter++;
	}

	// ==================
	// Getters and Setters

	public String getImmatriculation() {
		return immatriculation;
	}

	public int getBoatId() {
		return boatId;
	}

	public String getType() {
		return type;
	}

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPosition() {
		return position;
	}

	// ==================
	// Other methods
	/**
	 * Changes the state to "monitoring"
	 * 
	 * @see Boat#state
	 */

	public void monitor() {
		this.state = "monitoring";
	}

	/**
	 * Updates the new position of the boat and react accordingly
	 * 
	 * @param newPosition The new GPS position
	 * @return "not monitoring" if the boat is in this state, "monitoring" if
	 *         nothing happened, "stolen" if it just went out of the zone,
	 *         "tracking" if it is currently stolen and under tracking
	 * 
	 * @see Boat#state
	 */

	public String updatePosition(GPS newPosition) {
		int alarm = this.boatSecuZone.updatePosition(newPosition);
		this.position = this.boatSecuZone.toString();
		if (this.state.contentEquals("not monitoring")) {
			return "not monitoring";
		} else if ((alarm == 1) & (this.state.contentEquals("monitoring"))) {
			this.state = "stolen";
			this.state = "tracking";
			return "stolen";
		} else if ((alarm == 1) & (this.state.contentEquals("tracking"))) {
			this.logWriter.writePosition(newPosition);
			return "tracking";
		}
		return "monitoring";
	}

	/**
	 * Changes the state to "not monitoring"
	 * 
	 * @see Boat#state
	 */

	public void stopTracking() {
		this.state = "not monitoring";
	}

	@Override
	public String toString() {
		return "Boat [immatriculation=" + immatriculation + ", name=" + name + ", type=" + type + ", model=" + model
				+ ", place=" + place + ", state=" + state + "]";
	}

}