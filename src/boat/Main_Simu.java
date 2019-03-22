package boat;

import server.TCPServer;

public class Main_Simu {

	public static void main(String[] args) {
		
		GPS depart_initial  = new GPS(0,0); // On démarre à la position (0,0)
		GPS position_bateau = new GPS(0,0);
		double maxDistance = 4;
		double Distance_bateau_zone;
		SecuriteZone Zone_safe = new SecuriteZone(position_bateau, depart_initial,maxDistance);
		double position_x;
		double position_y;
		for (int i = 1; i<6; i++) {
			double d = (double)i;
			position_bateau.setLon(d);
			position_bateau.setLat(d);
			position_x = position_bateau.getLon();
			position_y = position_bateau.getLat();
			System.out.format("Distance maximale: %f\n",maxDistance);
			System.out.format("position x : %f ,  position y : %f\n", position_x, position_y);
			Zone_safe.update_position(position_bateau);
			Distance_bateau_zone = Zone_safe.compute_dist();
			System.out.format("Voici la distance entre le bateau et le centre de la zone : %f\n", Distance_bateau_zone);
			if (Distance_bateau_zone > maxDistance) {
				System.out.println("Le bateau est sorti de la zone");
				break;
			}
					
		}
	}
}
