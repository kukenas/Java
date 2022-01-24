package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AntiquBookBadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7235188424638216603L;

	// Default Constructor
	public AntiquBookBadRequestException() {
		super("Bad Request!");
	}

}
