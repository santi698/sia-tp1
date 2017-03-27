package ar.edu.itba.sia.g7.sokoban.exception;

public class TooManyPlayersInBoardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooManyPlayersInBoardException(String message){
		super(message);
	}
}
