package automatons;

/**
 * <b>IAutomaton is the automaton interface providing the need to override
 * connection and log out in the class implementing it</b>
 * <p>
 * 
 * @author Quentin Ducasse
 */

public interface IAutomaton {

	/**
	 * Connects to the server. Needs to be overridden
	 * 
	 * @return Confirmation of the connection
	 */

	public boolean connection();

	/**
	 * Logs out from the server. Needs to be overridden
	 */

	public void logout();

}
