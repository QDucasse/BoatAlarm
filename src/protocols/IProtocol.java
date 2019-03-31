package protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import server.IContext;

/**
 * <b>IProtocol is the interface defining the common behavior the central system
 * can use and the subclasses have to consider</b>
 * <p>
 * IProtocol and only has only one method 'execute' to decipher instructions
 * coming from an Automaton
 * </p>
 * 
 * @see IProtocol
 * @author Quentin Ducasse
 */

public interface IProtocol {

	public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) throws IOException;

}
