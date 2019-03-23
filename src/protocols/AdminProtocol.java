package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import server.IContext;
import users.Subscriber;

public class AdminProtocol implements IProtocol {

	public void execute(IContext aContext, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);

		while ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");

			/* add subscriber option: add --all infos-- */
			if (chaines[0].contentEquals("add")) {
				aContext.addSubscriber(new Subscriber(chaines[1], 				   //Account name
													  chaines[2], 			       //Password
													  chaines[3],		    	   //Name
													  chaines[4], 				   //Address
													  chaines[5], 				   //email
													  LocalDate.parse(chaines[6]), //Subscription date
													  chaines[7], 				   //Confidence email
													  chaines[8], 				   //Confidence phone number
													  chaines[9],				   //Boat immatriculation
													  chaines[10],				   //Boat name
													  chaines[11],				   //Boat Type
													  chaines[12] ));			   //Boat model
				System.out.println("Subscriber added, sending confirmation to the client!");
				os.println("Subscriber added!");
				os.flush();
			}
			

			/* delete subscriber option: delete accountname */
			if (chaines[0].contentEquals("delete")) {
				for (Subscriber s : aContext.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) {
						aContext.deleteSubscriber(s);
						System.out.println("Subscriber deleted, sending confirmation to the client!");
						os.println("Subscriber deleted!");
						os.flush();
					}
				}
			}
		}
	}
}
