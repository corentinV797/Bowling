package customException;
/**
 * 
 * @author Corentin V
 *
 */
public class InvalidPinsDownException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPinsDownException(String message) {
		super(message);
	}

}
