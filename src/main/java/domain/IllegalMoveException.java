package domain;

/**
 * Subclass of Excpetion<br>
 * Responsible for the throwable error of an illegal move in a ChessGame
 *
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 *
 */
public class IllegalMoveException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalMoveException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IllegalMoveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IllegalMoveException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalMoveException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalMoveException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
