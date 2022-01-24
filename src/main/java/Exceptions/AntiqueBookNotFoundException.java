package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AntiqueBookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2665847542809744632L;

	// Default Constructor
	public AntiqueBookNotFoundException() {
		super();
	}

	public AntiqueBookNotFoundException(int release) {
		super("AntiqueBook with a Release Date " + release + " does not exists.");
	}

	public AntiqueBookNotFoundException(long barcode) {
		super("AntiqueBook with Barcode " + barcode + " could not be found in Database.");
	}

}
