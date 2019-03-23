package protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import server.IContext;

public interface IProtocol {
	// ================
	// Abstract methods
	public String execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) throws IOException;

}
