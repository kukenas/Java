package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle all BadRequest related Exceptions
 * @author Aurimas
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7235188424638216603L;

	/**
	 * No arguments constructor calling superclass constructor
	 */
	public BadRequestException() {
		super("Request is not in line with API specifications.");
	}

}
