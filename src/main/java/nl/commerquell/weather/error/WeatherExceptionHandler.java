package nl.commerquell.weather.error;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class WeatherExceptionHandler {
	private Logger logger = Logger.getLogger(getClass().getName());

	@ExceptionHandler
	public ResponseEntity<WeatherErrorResponse> handleException(Exception x) {
		x.printStackTrace();
		HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;
		if (x instanceof HttpStatusCodeException) {
			httpStatus = ((HttpStatusCodeException) x).getStatusCode();
		}
		int status = httpStatus.value();
		WeatherErrorResponse error = new WeatherErrorResponse(status, x.getMessage(), System.currentTimeMillis());
		logger.log(Level.SEVERE, "HTTP status " + status + " returned. Error message: " + x.getMessage());
		return new ResponseEntity<>(error, httpStatus);
	}
}
