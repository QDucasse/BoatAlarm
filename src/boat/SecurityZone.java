package boat;

/**
 * <b>SecurityZone defines the zone where we consider the boat as not stolen</b>
 * <p>
 * A SecurityZone instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A GPS current position (where the boat is right now)</li>
 * <li>A GPS initial position (where the boat was parked)</li>
 * <li>A maximal distance corresponding to the radius of the zone</li>
 * <li>The distance from the center of the zone</li>
 * <li>An indicator of the alarm</li>
 * </ul>
 * 
 * @author Guillaume Le Boucher
 */

public class SecurityZone {
	// ==================
	// Instance Variables
	/**
	 * The current position
	 */
	private GPS currentPos;
	/**
	 * The initial position
	 */
	private GPS initialPos;
	/**
	 * The radius of the zone
	 */
	private double maxDistance;
	/**
	 * The actual distance from the center
	 */
	private double d; // Distance from the center of the security zone
	/**
	 * The indicator of the alarm
	 */
	private int alarm;

	// ==================
	// Constructors

	/**
	 * SecurityZone Constructor.
	 * <p>
	 * Through construction, the SecurityZone is initialized with position 0,0 if
	 * not chosen
	 * </p>
	 * 
	 * @param current_pos The current position of the boat
	 * @param initial_pos The initial position of the boat
	 * @param maxDistance The radius of the security zone
	 * @see SecurityZone
	 * @see SecurityZone#d
	 * @see SecurityZone#currentPos
	 * @see SecurityZone#initialPos
	 * @see SecurityZone#maxDistance
	 */

	public SecurityZone(GPS current_pos, GPS initial_pos, double maxDistance) {
		this.initialPos = initial_pos;
		this.currentPos = current_pos;
		this.maxDistance = maxDistance;
	}

	public SecurityZone(GPS current_pos, double maxDistance) {
		this(current_pos, new GPS(0, 0), maxDistance);
	}

	// ==================
	// Getters & Setters

	public GPS getCurrentPos() {
		return currentPos;
	}

	public GPS getInitialPos() {
		return initialPos;
	}

	public void setInitialPos(GPS initial_pos) {
		this.initialPos = initial_pos;
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

	public int getAlarm() {
		return alarm;
	}

	// ==================
	// Methods

	/**
	 * Computes the new distance from the center
	 * 
	 * @return distance from the center
	 */

	public double computeDist() {
		double distanceCenter;
		double currentLat = currentPos.getLat();
		double currentLon = currentPos.getLon();
		double initialLat = initialPos.getLat();
		double initialLon = initialPos.getLon();
		double dlat = Math.abs(currentLat - initialLat);
		double dlon = Math.abs(currentLon - initialLon);
		distanceCenter = Math.sqrt(Math.pow(dlat, 2) + Math.pow(dlon, 2));
		return distanceCenter;
	}

	/**
	 * Updates the position of the boat, computes the new distance from the center
	 * and updates the alarm
	 * 
	 * @param newPosition The new given position
	 * @return 1 if the alarm is triggered, 0 if not
	 * @see SecurityZone#updatePosition(GPS)
	 * @see SecurityZone#updateAlarm()
	 * @see SecurityZone#computeDist()
	 */

	public int updatePosition(GPS newPosition) {
		currentPos = newPosition;
		this.d = this.computeDist();
		return this.updateAlarm();
	}

	/**
	 * Checks if the actual distance form the center is greater than the maximal
	 * distance allowed
	 * 
	 * @return 1 if the alarm is triggered, 0 if not
	 */

	public int updateAlarm() {
		if (d >= maxDistance) {
			alarm = 1;
			return 1;
		}
		return 0;
	};

	@Override
	public String toString() {
		return "" + this.currentPos.getLat() + "," + this.currentPos.getLon();
	}

}