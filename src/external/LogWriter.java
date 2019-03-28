package external;

import java.io.*;
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
		this.file=new File("logs/tracking"+LocalDate.now()+"-"+this.boatName+".txt");
		try {
			writer = new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		writer.println("[" + LocalDateTime.now() +"]:" + newPosition.getLat()+" "  + newPosition.getLon() + "\n");
	}
}
