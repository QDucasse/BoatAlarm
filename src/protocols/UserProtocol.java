package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import server.IContext;
import users.Subscriber;

/**
 * <b>UserProtocol is the Protocol that knows how to decipher user-related
 * instructions coming from a UserAutomaton</b>
 * <p>
 * UserProtocol implements the IProtocol and only has one method 'execute' to
 * decipher the instructions
 * </p>
 * 
 * @see IProtocol
 * @author Quentin Ducasse
 */

public class UserProtocol implements IProtocol {

	/**
	 * Decipher user-related instructions: Login, Change account name, Change
	 * password, Get boat information, Get boat name, Get boat state and Monitor
	 */

	@Override
	public void execute(IContext context, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
		int logged = 0;

		while ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");

			/* login option: login accountname password */
			if (chaines[0].contentEquals("login")) {
				for (Subscriber s : context.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) { // Check username
						if (s.getPassword().equals(chaines[2])) { // Check password
							System.out.println("Login successful, sending to client");
							os.println("1 - Login successful!");
							os.flush();
							context.notifyLogin(s);
							logged = 1;
						} else {
							System.out.println("Login failed, sending to client");
							os.println("0 - Login failed!");
							os.flush();
						}
					}
				}
			}

			/* change account name option: changename oldname newname */
			if (chaines[0].contentEquals("changename")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							String oldName = s.getAccount();
							s.setAccount(chaines[2]); // Account name changed
							os.println("Name changed!");
							os.flush();
							context.notifyAccountNameChange(oldName, chaines[2]);
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* change password option: changepassword accountname oldpassword newpassword */
			if (chaines[0].contentEquals("changepassword")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							s.setPassword(chaines[2]);// Password changed
							os.println("Password changed!");
							os.flush();
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* get boat info option: getboatinfo */
			if (chaines[0].contentEquals("getboatinfo")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							if (s.getBoat().getName().equals(chaines[2])) {
								// Write all infos in the buffer
							}
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* get boat name option: getboatname */
			if (chaines[0].contentEquals("getboatname")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							System.out.println("Boat name returned!");
							os.println(s.getBoat().getName());
							os.flush();
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* get boat state option: getboatstate */
			if (chaines[0].contentEquals("getboatstate")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							System.out.println("Boat state returned!");
							os.println(s.getBoat().getState());
							os.flush();
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* monitor option: monitor boatname */
			if (chaines[0].contentEquals("monitor")) {
				if (logged == 1) {
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							if (s.getBoat().getName().equals(chaines[2])) {
								s.getBoat().setState("monitoring");
								System.out.println("Boat is now monitored!");
								os.println("Your boat is now monitored");
								os.flush();
								context.notifyMonitoring(s);
							} else {
								System.out.println(s.getName() + ": No boat named that way!");
								os.println("You do not own a boat with that name");
								os.flush();
							}
						}
					}
				} else {
					os.println("You need to login first");
					os.flush();
				}
			}
		}

		logged = 0;

	}
}
