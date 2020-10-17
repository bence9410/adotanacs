package hu.beni.tax.exception;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaxRestAdvice {

	private static final String ERROR = "Error:";

	private static final String VALIDATION_ERROR_FIELD_MESSAGE = "Validation error: %s %s. ";

	private static final String VALIDATION_ERROR_MESSAGE = "Validation error: %s. ";

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public String handleThrowable(Throwable throwable) {
		log.error(ERROR, throwable);
		return "Unexpected error occured!";
	}

	@ExceptionHandler(TaxException.class)
	public String handleTaxException(TaxException taxException) {
		log.error(ERROR, taxException);
		return taxException.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		log.error(ERROR, methodArgumentNotValidException);
		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		return Stream
				.concat(bindingResult.getFieldErrors().stream().map(this::convertToMessage),
						bindingResult.getGlobalErrors().stream().map(this::convertToMessage))
				.reduce(String::concat).orElse("Could not get validation error message");
	}

	private String convertToMessage(FieldError fieldError) {
		return validationError(fieldError.getField(), fieldError.getDefaultMessage());
	}

	private String convertToMessage(ObjectError objectError) {
		return validationError(objectError.getDefaultMessage());
	}

	public static String validationError(String field, String message) {
		return String.format(VALIDATION_ERROR_FIELD_MESSAGE, field, message);
	}

	public static String validationError(String message) {
		return String.format(VALIDATION_ERROR_MESSAGE, message);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		log.error(ERROR, httpMessageNotReadableException);
		return "Request body is required!";
	}

}
