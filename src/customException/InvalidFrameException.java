package customException;
/**
 * 
 * @author Corentin V
 *
 */
public class InvalidFrameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidFrameException(String message) {
		super(message);
	}

}
