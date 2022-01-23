package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AntiqueBookNotExistException extends RuntimeException {

	private static final long serialVersionUID = 2665847542809744632L;

	public AntiqueBookNotExistException(int release) {
		super("AntiqueBook with a Release Date " + release + " does not exists!");
	}

}
