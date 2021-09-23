package customException;

public class NotEnoughFramesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughFramesException(String message) {
		super(message);
	}
}
