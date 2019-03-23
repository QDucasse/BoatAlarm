package protocols;

import java.io.InputStream;
import java.io.OutputStream;

import server.IContext;

public class StateProtocol implements IProtocol {

	@Override
	public String execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {
		return "";
	}
}
