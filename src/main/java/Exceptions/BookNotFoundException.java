package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6601811884227591945L;

	// Default Constructor
	public BookNotFoundException() {
		super();
	}
	
	public BookNotFoundException(Long barcode) {
		super("Not able to find Book with barcode " + barcode);
	}

}
