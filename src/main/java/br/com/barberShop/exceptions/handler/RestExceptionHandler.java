package br.com.barberShop.exceptions.handler;

import br.com.barberShop.constants.ErrorCodes;
import br.com.barberShop.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UnableJWTException.class)
	public ResponseEntity<Object> unableJWT(UnableJWTException msg){
		ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.UNABLE_TO_GET_JWT, msg.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
	}

	@ExceptionHandler(ExpiredJWTException.class)
	public ResponseEntity<Object> expiredJWTException(ExpiredJWTException msg){
		ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.JWT_EXPIRED, msg.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
	}

	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
		log.error("Bad Request Exception - ", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		log.error("RestExceptionHandler.handleNotFoundException - NotFoundException", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
	}

	@ExceptionHandler(CustomerException.class)
	public final ResponseEntity<Object> handleCustomerException(CustomerException ex) {
		log.error("RestExceptionHandler.handleViaCepClientException - Customer Exception", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.INTERNAL_SERVER_ERROR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
	}
}
