package protocols;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import server.IContext;

public class TestProtocol implements IProtocol {
	
	public String execute(IContext context,InputStream input , OutputStream output ) 
			throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
		String outputMessage = "";
		
		try {
			if ((inputLine = is.readLine()) != null) {
				System.out.println("Message received" + inputLine);
				String words[] = inputLine.split(" ");

				if (words[0].contentEquals("Test Launched!")) {
					outputMessage = "Test Passed!";
					System.out.println("Server Answer: " + outputMessage);
					os.println(outputMessage);
				}
			}
		} catch ( Exception e) {
			System.out.println("Exception caught");
		}
		return outputMessage;		
	}
	
}
