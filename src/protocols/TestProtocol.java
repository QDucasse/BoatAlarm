package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import server.IContext;

public class TestProtocol implements IProtocol {

	public void execute(IContext context, InputStream input, OutputStream output) throws IOException {
		String inputLine;
		BufferedReader is = new BufferedReader(new InputStreamReader(input));
		PrintStream os = new PrintStream(output);
	
		try {
			String outputMessage = "";
			if ((inputLine = is.readLine()) != null) {
				System.out.println("Message received" + inputLine);

				if (inputLine.contentEquals("Test Launched!")) {
					outputMessage = "Test Passed!";
					System.out.println("Server Answer: " + outputMessage);
					
				}
				os.println(outputMessage);
			}
		} catch (Exception e) {
			System.out.println("Exception caught");
		}
	}

}
