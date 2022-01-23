package Exceptions;

public class NoBooksFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -3091578006399512534L;

	public NoBooksFoundException (String message){
		super(message);
	}

}
