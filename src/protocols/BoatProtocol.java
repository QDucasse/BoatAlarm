package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import boat.GPS;
import external.EmailSender;
import server.IContext;
import users.Subscriber;

/**
 * <b>BoatProtocol is the Protocol that knows how to decipher boat-related
 * instructions coming from a BoatAutomaton</b>
 * <p>
 * BoatProtocol implements the IProtocol and only has one method 'execute' to
 * decipher the instructions
 * </p>
 * 
 * @see IProtocol
 * @author Quentin Ducasse
 */

public class BoatProtocol implements IProtocol {

	/**
	 * Decipher boat-related instructions: Transmit GPS data and sends an email in
	 * case of theft
	 */

	@Override
	public void execute(IContext context, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
		EmailSender emailSender = new EmailSender();

		while ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");

			/* gpsData option: gpsData accountName boatName lat lon */
			if (chaines[0].contentEquals("gpsdata")) {
				for (Subscriber s : context.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) { // Check username
						if (s.getBoat().getName().equals(chaines[2])) { // Check password
							String res = "";
							res = s.getBoat().updatePosition(
									new GPS(Double.parseDouble(chaines[3]), Double.parseDouble(chaines[4])));
							context.notifyStateChange(s.getBoat().getName(), res);
							if (res.contentEquals("stolen")) { // Send an email if the boat is stolen
								try {
									emailSender.sendEmailToSubscriber(s);
								} catch (AddressException e) {
									e.printStackTrace();
								} catch (MessagingException e) {
									e.printStackTrace();
								}
							}
							System.out.println("Identification successful, position transmitted");
							os.println("1 - Position transmited!");
							os.flush();
						} else {
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
