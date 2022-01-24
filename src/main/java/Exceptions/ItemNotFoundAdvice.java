package Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ItemNotFoundAdvice extends ResponseEntityExceptionHandler {

	public ItemNotFoundAdvice() {
		super();
	}

	@ExceptionHandler({ BookNotFoundException.class, AntiqueBookNotExistException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleResourceNotFound(final RuntimeException exception,
			final WebRequest request) {
		return handleExceptionInternal(exception, exception, new HttpHeaders(), HttpStatus.NOT_FOUND,
				(WebRequest) request);
	}
}
