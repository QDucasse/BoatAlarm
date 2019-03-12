package protocols;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.*;

import server.IContext;
import users.Subscriber;


public class AdminProtocol implements IProtocol{

	public String execute(IContext aContext,InputStream input , OutputStream output ) 
			throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		String var="";
		
		if ((inputLine = is.readLine()) != null) {
			String chaines[] = inputLine.split(" ");
			
			/* add subscriber option: add --all infos-- */
			if (chaines[0].contentEquals("add")) {
				for(Subscriber s : aContext.getSubscriberList()){
					if (s.getAccount().equals(chaines[1])){
						var=s.getAccount();
						return "Subscriber added!";
					}	
				}
				
			}
			
			/* delete subscriber option: delete accountname */
			if (chaines[0].contentEquals("delete")) {
				for(Subscriber s : aContext.getSubscriberList()){
					if (s.getAccount().equals(chaines[1])){
						aContext.deleteSubscriber(s);
						return "Subscriber deleted!";
					}
				}
				
			}
			
		}
		
		return "help blabla";
	}
	
}
