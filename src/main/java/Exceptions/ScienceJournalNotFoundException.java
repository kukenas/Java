package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ScienceJournalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3389743323056860942L;

	// Default Constructor
	public ScienceJournalNotFoundException() {
		super();
	}

	public ScienceJournalNotFoundException(int index) {
		super("ScienceJournal with Index" + index + " does not exists.");
	}

	public ScienceJournalNotFoundException(long barcode) {
		super("ScienceJournal with Barcode " + barcode + " could not be found in Database.");
	}

}
