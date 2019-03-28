package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import boat.GPS;
import server.IContext;
import users.Subscriber;

public class BoatProtocol implements IProtocol {

	@Override
	public void execute(IContext context, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
		
		while ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");

			/* gpsData option: gpsData accountName boatName lat lon */		
			if (chaines[0].contentEquals("gpsdata")) {
				for (Subscriber s : context.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) { 		    //Check username
						if (s.getBoat().getName().equals(chaines[2])) {	//Check password
							s.getBoat().updatePosition(new GPS(Double.parseDouble(chaines[3]),Double.parseDouble(chaines[4])));
							
							System.out.println("Identification successful, position transmitted");
							os.println("1 - Position transmited!");
							os.flush();
						}
						else {
							System.out.println("The client name does not correspond to this boat");
							os.println("0 - Identification failed!");
							os.flush();
						}
						System.out.println(s.getBoat().toString());
					}
				}
			}
		}
	}
}
