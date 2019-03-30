package boat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class SecurityZoneTest {
	
	private static GPS initial_pos = new GPS(0,0);;
	private static GPS current_pos = new GPS(3,4);;
	private static double maxDistance = 2;
	private static SecurityZone securityZone = new SecurityZone(current_pos,initial_pos,maxDistance);;
	
	@Before
	public static void setUp() {
		initial_pos = new GPS(0,0);
		current_pos = new GPS(3,4);
		maxDistance = 2;
		securityZone = new SecurityZone(current_pos,initial_pos,maxDistance);
	}

	@Test
	public void testCompute_dist() {
		double computed_dist = securityZone.computeDist();
		assertEquals(computed_dist,5);
	}

	@Test
	public void testUpdatePositionNoAlarm() {
		GPS newPosition = new GPS(4,5);
		int alarm = securityZone.updatePosition(newPosition);
		assertEquals(securityZone.getCurrent_pos().getLat(), 4);
		assertEquals(securityZone.getCurrent_pos().getLon(), 5);
		assertEquals(alarm,1);
	}
	
	@Test
	public void testUpdatePositionAlarm() {
		GPS newPosition = new GPS(0,1);
		int alarm = securityZone.updatePosition(newPosition);
		assertEquals(securityZone.getCurrent_pos().getLat(), 0);
		assertEquals(securityZone.getCurrent_pos().getLon(), 1);
		assertEquals(alarm,0);
	}

	@Test
	public void testUpdateAlarmTriggered() {
		GPS newPosition = new GPS(5,5);
		securityZone.updatePosition(newPosition);
		assertEquals(1,securityZone.updateAlarm());
	}
	
	@Test
	public void testUpdateAlarmNotTriggered() {
		GPS newPosition = new GPS(1,1);
		securityZone.updatePosition(newPosition);
		assertEquals(0,securityZone.updateAlarm());
	}

}
