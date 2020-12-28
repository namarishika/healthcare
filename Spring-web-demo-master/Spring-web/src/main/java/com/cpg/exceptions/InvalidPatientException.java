package com.cpg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Invalid Details")
public class InvalidPatientException extends RuntimeException {
	
	public InvalidPatientException(String message) {
		super(message);
	}

}
