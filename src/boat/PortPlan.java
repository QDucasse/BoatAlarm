package boat;

import java.util.HashMap;
import java.util.Map;

public class PortPlan {
	Map<String,GPS> portPlan = new HashMap<String, GPS>();
	
	public PortPlan() {
		GPS gps0 = new GPS(0,0);
		GPS gps1 = new GPS(0,1);
		GPS gps2 = new GPS(0,2);
		GPS gps3 = new GPS(0,3);
		GPS gps4 = new GPS(0,4);
		GPS gps5 = new GPS(0,5);
		GPS gps6 = new GPS(0,6);
		GPS gps7 = new GPS(0,7);
		GPS gps8 = new GPS(0,8);
		GPS gps9 = new GPS(0,9);
		
		portPlan.put("0" , gps0);
		portPlan.put("1" , gps1);
		portPlan.put("2" , gps2);
		portPlan.put("3" , gps3);
		portPlan.put("4" , gps4);
		portPlan.put("5" , gps5);
		portPlan.put("6" , gps6);
		portPlan.put("7" , gps7);
		portPlan.put("8" , gps8);
		portPlan.put("9" , gps9);
	}
}
