package customException;
/**
 * 
 * @author Corentin V
 *
 */
public class TooManyFramesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooManyFramesException(String message) {
		super(message);
	}
}
