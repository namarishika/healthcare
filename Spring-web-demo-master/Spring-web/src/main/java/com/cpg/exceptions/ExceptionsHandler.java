package com.cpg.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;





@RestControllerAdvice
public class ExceptionsHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

	@ExceptionHandler
	public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
		
		logger.error(exception.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDetails> handleInvalidPatientException(InvalidPatientException exception, WebRequest request) {
		
		logger.error(exception.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handlerGlobalException
		(Exception exception, WebRequest request) {
		
		logger.error(exception.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
