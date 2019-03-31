package external;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import boat.GPS;

/**
 * <b>LogWriter defines the helper that creates and writes in the log files</b>
 * <p>
 * A LogWriter instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A boat name</li>
 * <li>A file to write the logs in</li>
 * <li>A writer</li>
 * </ul>
 *
 * @author Quentin Ducasse
 */

public class LogWriter {
	/**
	 * The boat name
	 */
	private String boatName;
	/**
	 * The log file
	 */
	File file;
	/**
	 * The file writer
	 */
	PrintStream writer = null;

	/**
	 * LogWriter Constructor.
	 * <p>
	 * Through construction, the LogWriter simply gathers the boat name
	 * </p>
	 * 
	 * @param boatName The name of the boat
	 * @see LogWriter
	 * @see LogWriter#boatName
	 */

	public LogWriter(String boatName) {
		this.boatName = boatName;
	}

	/**
	 * Writes the given position in the file that corresponds and creates it if
	 * non-existent
	 * 
	 * @param newPosition The current position of the boat
	 */

	public void writePosition(GPS newPosition) {
		String log = "[" + LocalDateTime.now() + "]:" + newPosition.getLat() + " " + newPosition.getLon() + "\n";
		try {
			Files.write(Paths.get("logs/" + this.boatName + "-" + LocalDate.now() + ".txt"), log.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
