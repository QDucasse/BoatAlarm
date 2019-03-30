package external;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import boat.GPS; 

public class LogWriter {
	private String boatName;
	File file;
	PrintStream writer = null;
	
	public LogWriter(String boatName) {
		this.boatName = boatName;
	}
	
	public void writePosition(GPS newPosition) {
		String log = "[" + LocalDateTime.now() +"]:" + newPosition.getLat()+" "  + newPosition.getLon() + "\n";
		try {
			Files.write(Paths.get("logs/"+this.boatName+"-"+LocalDate.now()+".txt"), log.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
