package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import boat.Boat;
import server.IContext;
import users.Subscriber;

public class UserProtocol implements IProtocol {

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
					if (s.getAccount().equals(chaines[1])) { 		//Check username
						if (s.getPassword().equals(chaines[2])) {	//Check password
							System.out.println("Login successful, sending to client");
							os.println("1 - Login successful!");
							os.flush();
							logged = 1;
						}
						else {
							System.out.println("Login failed, sending to client");
							os.println("0 - Login failed!");
							os.flush();
						}
					} 
				}
			}

			/* change account name option: changename oldname newname */
			if (chaines[0].contentEquals("changename")) {
				if (logged==1){	
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							s.setAccount(chaines[2]); //Account name changed
							os.println("Name changed!");
							os.flush();
						}
					}
				}
				else {
					os.println("You need to login first");
					os.flush();
				}
			}
			
			
			/* change password option: changepassword accountname oldpassword newpassword */
			if (chaines[0].contentEquals("changepassword")) {
				if (logged==1){	
					for (Subscriber s : context.getSubscriberList()) {
						if (s.getAccount().equals(chaines[1])) {
							s.setPassword(chaines[2]);//Password changed
							os.println("Password changed!");
							os.flush();
						}
					}
				}
				else {
					os.println("You need to login first");
					os.flush();
				}
			}

			/* get boat info option: getboatinfo */
			if (chaines[0].contentEquals("getboatinfo boatname")) {
				if (logged==1) {	
					for (Boat b : context.getBoatList()) {
						if (b.getName().equals(chaines[1])) {
							//Write all infos in the buffer
						}
					}
				}
				else {
					os.println("You need to login first");
					os.flush();
				}
			}
		}
		
		logged = 0;
		
	}
}
