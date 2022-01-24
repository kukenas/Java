package Exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ControllerAdvice connected to {@link BookNotFoundException} and
 * {@link AntiqueBookNotFoundException}
 * 
 * @author Aurimas
 *
 */
@ControllerAdvice
public class CustomController extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(CustomController.class);

	/**
	 * No arguments constructor calling superclass constructor
	 */
	public CustomController() {
		super();
	}

	/**
	 * Method handles "Not Found" Exceptions
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler({ BookNotFoundException.class, AntiqueBookNotFoundException.class,
			ScienceJournalNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
		LOG.info("Method handleResourceNotFound() initialized by RuntimeException: ", ex);
		String body = "Requested resource could not be found.";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, body, httpHeaders, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(AntiquBookBadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleInvalidInput(RuntimeException ex, WebRequest request) {
		LOG.info("Method handleInvalidInput() initialized by RuntimeException: ", ex);
		String body = "Request is not in line with API specifications.";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, body, httpHeaders, HttpStatus.NOT_FOUND, request);
	}
}
