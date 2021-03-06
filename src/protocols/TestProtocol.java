package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import server.IContext;

/**
 * <b>TestProtocol is the Protocol that tries the connection, shipment and
 * receipt of messages through TCP</b>
 * <p>
 * TestProtocol implements the IProtocol and only has one method 'execute' to
 * decipher the instructions
 * </p>
 * 
 * @see IProtocol
 * @author Quentin Ducasse
 */

public class TestProtocol implements IProtocol {
	
	/**
	 * Decipher only one instruction: 'Test Launched!' and replies 'Test Passed!'
	 */
	
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
					os.println("Test Passed!");
					os.flush();

				}
				os.println(outputMessage);
			}
		} catch (Exception e) {
			System.out.println("Exception caught");
		}
	}

}
