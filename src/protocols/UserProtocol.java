package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import server.IContext;
import users.Subscriber;
import boat.Boat;

public class UserProtocol implements IProtocol {

	public void execute(IContext context, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
		String var = "";

		if ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");

			/* login option: login accountname password */
			if (chaines[0].contentEquals("login")) {
				for (Subscriber s : context.getSubscriberList()) {
					if ((s.getAccount().equals(chaines[1])) & (s.getPassword().equals(chaines[2]))) {
						os.println("Login successful!");
						os.flush();
					} else {
						os.println("Login failed!");
						os.flush();
					}
				}

			}

			/* change account name option: changename oldname newname */
			if (chaines[0].contentEquals("changename")) {
				for (Subscriber s : context.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) {
						s.setAccount(chaines[2]); //Account name changed
						//Write in the buffer
					}
				}

			}
			/* change password option: changepassword oldpassword newpassword */
			if (chaines[0].contentEquals("changepassword")) {
				for (Subscriber s : context.getSubscriberList()) {
					if (s.getAccount().equals(chaines[1])) {
						s.setPassword(chaines[2]);//Password changed
						//Write in the buffer
					}
				}

			}

			/* get boat info option: getboatinfo */
			if (chaines[0].contentEquals("getboatinfo boatname")) {
				for (Boat b : context.getBoatList()) {
					if (b.getName().equals(chaines[1])) {
						//Write all infos in the buffer
					}
				}
			}
		}
	}
}
