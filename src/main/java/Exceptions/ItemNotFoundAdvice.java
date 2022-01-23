package Exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemNotFoundAdvice {

	@ExceptionHandler({ NoBooksFoundException.class, BookNotFoundException.class, AntiqueBookNotExistException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody String handleResourceNotFound(final RuntimeException exception,
			final HttpServletRequest request) {
		return exception.getMessage();
	}
}
