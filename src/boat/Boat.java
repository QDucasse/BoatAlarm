package boat;
public class Boat{
	
	//==================
	//Instance Variables
	
	private int boatID; 	// dans le constructeur il faut prendre l'id le plus grands de la liste des 
				   	   //  boat ce qui est normalement la dernier dans la liste et ajouter l'id +1 pour le new Boat.
	private int immatriculation;
	private String name;
	private String type;
	private String model;
	private String position;
	private String place;
	private int state;
	
	//==================
	//Constructors
	
	public Boat(int immatriculation, String name, String type, String model)
	{
	
		// il faut ajouter la valeur de cette id dans le consructeur
		this(immatriculation, name, type, model, "0,0", "port", 0);
	}
	
	//il faut ajouter id dans cet constructeur 
	public Boat(int immatriculation, String name, String type, String model,String position, String place, int state)
	{
		this.immatriculation =immatriculation;
		this.name = name;
		this.type = type;
		this.model = model;
		this.position = position;
		this.place = place;
		this.state = state;
	}
	
	//==================
	//Getters and Setters
	
	public int getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(int immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	//==================
	//Other methods
	
	@Override
	public String toString() {
		return "Boat [immatriculation=" + immatriculation + ", name=" + name + ", type=" + type
				+ ", model=" + model + ", position=" + position + ", place=" + place
				+ ", state=" + state + "]";
	}
	
	

	
	
	
}