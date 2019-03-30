package boat;

import external.LogWriter;

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

	public Boat(String immatriculation, String name, String type, String model) {
		this(immatriculation, name, type, model, "0,0", "port", "not monitoring");
	}
	
	public Boat(String immatriculation, String name, String type, String model, String position, String place, String state) {
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

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPosition(String position) {
		this.position = position;
	}

	public SecurityZone getBoatSecuZone() {
		return boatSecuZone;
	}

	// ==================
	// Other methods
	
	public void monitor() {
		this.state = "monitoring";
	}
	
	public String updatePosition(GPS newPosition) {
		int alarm = this.boatSecuZone.updatePosition(newPosition);
		GPS currentPosition = this.boatSecuZone.getCurrent_pos();
		this.position = currentPosition.toString();
		if((alarm==1)&(this.state.contentEquals("not monitoring"))) {
			return "not monitoring";
		}
		else if ((alarm==1)&(this.state.contentEquals("monitoring"))){
			this.state = "stolen";
			this.state = "tracking";
			return "stolen";
		}
		else if ((alarm==1)&(this.state.contentEquals("tracking"))) {
			this.logWriter.writePosition(newPosition);
			return "tracking";
		}
		return "";
	}
	
	public void stopTracking() {
		this.state = "not monitoring";
	}

	@Override
	public String toString() {
		return "Boat [immatriculation=" + immatriculation + ", name=" + name + ", type=" + type + ", model=" + model
				+ ", place=" + place + ", state=" + state + "]";
	}

}