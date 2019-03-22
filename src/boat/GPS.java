package boat;

public class GPS {
	private double lon;
	private double lat;

	public GPS(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public double getLon() {
		return lon;
	}
	
	public double setLon(double lon) {
		this.lon = lon;
		return lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "GPS [lon=" + lon + ", lat=" + lat + "]";
	}
	
	
	
}