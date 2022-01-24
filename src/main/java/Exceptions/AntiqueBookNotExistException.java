package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AntiqueBookNotExistException extends RuntimeException {

	private static final long serialVersionUID = 2665847542809744632L;
	
	// Default Constructor
	public AntiqueBookNotExistException () {
		super();
	}

	public AntiqueBookNotExistException(int release) {
		super("AntiqueBook with a Release Date " + release + " does not exists!");
	}

	public AntiqueBookNotExistException(long barcode) {
		super ("AntiqueBook with a Release Date " + barcode + " could not be found in Database!");
	}

}
