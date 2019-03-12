package protocols;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.*;

import server.IContext;
import users.Subscriber;


public class UserProtocol implements IProtocol {
	
	public String execute(IContext context,InputStream input , OutputStream output ) 
			throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		String var="";
		
		if ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");
			
			/* change account name option: changename oldname password newname */
			if (chaines[0].contentEquals("changename")) {
				for(Subscriber s : context.getSubscriberList()){
					if (s.getAccount().equals(chaines[1])){
						var=s.getAccount();
						return var;
					}	
				}
				
			}
			/* change password option: changepassword accountname oldpassword newpassword */
			if (chaines[0].contentEquals("changepassword")) {
				for(Subscriber s : context.getSubscriberList()){
					if (s.getAccount().equals(chaines[1])){
						var=s.getAccount();
						return var;
					}	
				}
				
			}
			
			/* get boat info option: getboatinfo */
			if (chaines[0].contentEquals("getboatinfo acountname")) {
				for(Subscriber s : context.getSubscriberList()){
					if (s.getAccount().equals(chaines[1])){
						var=s.getAccount();
						return var;
					}	
				}
				
			}
			
		}
		
		return "help blabla";
	}
}
