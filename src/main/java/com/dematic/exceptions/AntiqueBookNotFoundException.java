package com.dematic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle AntiqueBook related Exceptions
 * @author Aurimas
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AntiqueBookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2665847542809744632L;

	/**
	 * No arguments Constructor calling superclass constructor
	 */
	public AntiqueBookNotFoundException() {
		super();
	}

	/**
	 * Constructor calling superclass constructor by provided Release argument
	 * @param release
	 */
	public AntiqueBookNotFoundException(int release) {
		super("AntiqueBook with a Release Date " + release + " does not exists.");
	}

	/**
	 * Constructor calling superclass constructor by provided Barcode argument
	 * @param barcode
	 */
	public AntiqueBookNotFoundException(long barcode) {
		super("AntiqueBook with Barcode " + barcode + " could not be found in Database.");
	}

}
