package server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IProtocole {
	//================
	//Abstract methods
	public String execute( IContext aContext , InputStream anInputStream , OutputStream anOutputStream ) throws IOException;
	
}
