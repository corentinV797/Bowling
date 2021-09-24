package customException;
/**
 * 
 * @author Corentin V
 *
 */
public class TooManyRollsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooManyRollsException(String message) {
		super(message);
	}
}
