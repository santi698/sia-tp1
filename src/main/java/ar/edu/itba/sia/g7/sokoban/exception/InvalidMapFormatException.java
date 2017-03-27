package ar.edu.itba.sia.g7.sokoban.exception;

public class InvalidMapFormatException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMapFormatException(String errorMessage){
		super(errorMessage);
	}
}
