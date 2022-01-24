package com.dematic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to handle ScienceJournal related Exceptions
 * 
 * @author Aurimas
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ScienceJournalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3389743323056860942L;

	/**
	 * No arguments constructor calling superclass constructor
	 */
	public ScienceJournalNotFoundException() {
		super();
	}

	/**
	 * Constructor calling superclass constructor by provided Index argument
	 * 
	 * @param index
	 */
	public ScienceJournalNotFoundException(int index) {
		super("ScienceJournal with Index " + index + " does not exists.");
	}

	/**
	 * Constructor calling superclass constructor by provided Barcode argument
	 * 
	 * @param barcode
	 */
	public ScienceJournalNotFoundException(long barcode) {
		super("ScienceJournal with Barcode " + barcode + " could not be found in Database.");
	}

}
