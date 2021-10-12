package com.library.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.exception.ReserveBookNotFoundException;
import com.library.model.ErrorRespond;

@ControllerAdvice
public class ReserveBookAdvice {
	@ResponseBody
	@ExceptionHandler(ReserveBookNotFoundException.class)
	public ResponseEntity<?> reserveNotFoundHandler(Exception e) {
		ErrorRespond error = new ErrorRespond();
		error.setStatus(HttpStatus.NOT_FOUND + "," + HttpStatus.NOT_FOUND.getReasonPhrase());
		error.setMessage(e.getMessage());
		error.setDetails("You can't make any action on non-existing resource");

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
