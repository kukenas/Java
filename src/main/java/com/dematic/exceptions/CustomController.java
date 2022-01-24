package com.dematic.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dematic.utils.JsonUtil;

/**
 * ControllerAdvice connected to:
 * <P>
 * {@link BookNotFoundException}, {@link AntiqueBookNotFoundException},
 * {@link ScienceJournalNotFoundException} {@link BadRequestException},
 * 
 * 
 * @author Aurimas
 *
 */
@ControllerAdvice
public class CustomController extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(CustomController.class);

	public static final String ERROR = "error";

	/**
	 * No arguments constructor calling superclass constructor
	 */
	public CustomController() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOG.info("Method handleMissingServletRequestParameter() initialized with the following message: ", ex);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, new JSONObject().put(ERROR, ex.getMessage()).toString(), httpHeaders,
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ BookNotFoundException.class, AntiqueBookNotFoundException.class,
			ScienceJournalNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {

		LOG.info("Method handleResourceNotFound() initialized by RuntimeException: ", ex);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, new JSONObject().put(ERROR, ex.getMessage()).toString(), httpHeaders,
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleInvalidInput(RuntimeException ex, WebRequest request) {

		LOG.info("Method handleInvalidInput() initialized by RuntimeException: ", ex);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, new JSONObject().put(ERROR, ex.getMessage()).toString(), httpHeaders,
				HttpStatus.NOT_FOUND, request);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleViolatedConstraint(RuntimeException ex, WebRequest request) {

		LOG.error("Method handleViolatedConstraint() initialized by ConstraintViolationException: ", ex);

		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String error = sw.toString();
		String body = error.substring(error.indexOf("{") + 1, error.indexOf("}\'}") + 2).replaceAll("=", ":")
				.replaceAll("'\\{", "").replaceAll("\\}'", "");
		JSONObject jobj = new JSONObject();

		// Reconstruct String
		JsonUtil.constructJson(jobj, body);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, jobj.toString(), httpHeaders, HttpStatus.BAD_REQUEST, request);
	}
}
