package boat;

/**
 * <b>GPS is the model of positioning in our system</b>
 * <p>
 * A GPS instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A longitude</li>
 * <li>A latitude</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class GPS {
	/**
	 * The longitude
	 */
	private double lon;
	/**
	 * The latitude
	 */
	private double lat;

	/**
	 * GPS Constructor.
	 * <p>
	 * Through construction, the GPS is initialized with the given position
	 * </p>
	 * 
	 * @param lat The given latitude
	 * @param lon The given longitude
	 * 
	 * @see GPS
	 * @see GPS#lon
	 * @see GPS#lat
	 */

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