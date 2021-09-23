package customException;

public class NotEnoughRollsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughRollsException(String message) {
		super(message);
	}
}
