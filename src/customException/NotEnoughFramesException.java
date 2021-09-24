package customException;
/**
 * 
 * @author Corentin V
 *
 */
public class NotEnoughFramesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughFramesException(String message) {
		super(message);
	}
}
