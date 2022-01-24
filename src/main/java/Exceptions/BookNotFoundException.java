package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle all Book related Exceptions
 * @author Aurimas
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6601811884227591945L;

	/**
	 * No arguments constructor
	 */
	public BookNotFoundException() {
		super();
	}
	/**
	 * Constructor calling superclass constructor by provided Barcode argument
	 * @param barcode
	 */
	public BookNotFoundException(Long barcode) {
		super("Not able to find Book with barcode " + barcode);
	}

}
