package boat;

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

	// ==================
	// Constructors

	public Boat(String immatriculation, String name, String type, String model) {
		// Need to add the global variable instead of 1
		this(immatriculation, name, type, model, "0,0", "port", "not monitoring");
	}

	// il faut ajouter id dans cet constructeur
	public Boat(String immatriculation, String name, String type, String model, String position, String place, String state) {
		this.boatId = boatCounter;
		this.immatriculation = immatriculation;
		this.name = name;
		this.type = type;
		this.model = model;
		this.position = position;
		this.place = place;
		this.state = state;
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

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public SecurityZone getBoatSecuZone() {
		return boatSecuZone;
	}

	// ==================
	// Other methods

	@Override
	public String toString() {
		return "Boat [immatriculation=" + immatriculation + ", name=" + name + ", type=" + type + ", model=" + model
				+ ", place=" + place + ", state=" + state + "]";
	}

}