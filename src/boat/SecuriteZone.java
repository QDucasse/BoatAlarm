package boat;

public class SecuriteZone  {
	//==================
	//Instance Variables

	static double RAYON = 4;
	private GPS current_pos;
	private GPS initial_pos;
	private double maxDistance;
    private double d;
	
	//==================
	//Constructors
	
    public SecuriteZone(GPS current_pos, GPS initial_pos, double maxDistance) {
    	this.initial_pos = initial_pos;
    	this.current_pos = current_pos;
    	this.maxDistance= maxDistance;
    }
    
    
    public SecuriteZone(GPS current_pos, double maxDistance) {
    	this(current_pos, new GPS(0,0), maxDistance);
    }
    
    //==================
    //Getters & Setters
    
	public GPS getCurrent_pos() {
		return current_pos;
	}


	public void setCurrent_pos(GPS current_pos) {
		this.current_pos = current_pos;
	}


	public GPS getInitial_pos() {
		return initial_pos;
	}


	public void setInitial_pos(GPS initial_pos) {
		this.initial_pos = initial_pos;
	}


	public double getMaxDistance() {
		return maxDistance;
	}


	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}


	public double getD() {
		return d;
	}


	public void setD(double d) {
		this.d = d;
	}

	//==================
	//Methods
	
	public double compute_dist() {
		double distance_centre;
		double current_lat = current_pos.getLat();
		double current_lon = current_pos.getLon();
		double initial_lat = initial_pos.getLat();
		double initial_lon = initial_pos.getLon();
		System.out.format("pos_courrent : %f,%f  pos_init : %f,%f\n", current_lat,current_lon,initial_lat,initial_lon);
		double dlat = Math.abs(current_lat - initial_lat);
		double dlon = Math.abs(current_lon - initial_lon);
		distance_centre = Math.sqrt(Math.pow(dlat, 2) + Math.pow(dlon, 2));
		return distance_centre;
	}
	
	public void update_position(GPS new_position) {
		this.setCurrent_pos(new_position);
		//this.compute_dist();
	}
	
}