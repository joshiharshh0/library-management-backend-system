package com.library.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.exception.BookNotFoundException;
import com.library.model.ErrorRespond;

@ControllerAdvice
public class BookAdvice {
	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<?> bookNotFoundHandler(BookNotFoundException e) {
		ErrorRespond error = new ErrorRespond();
		error.setStatus(HttpStatus.NOT_FOUND.value() + "," + HttpStatus.NOT_FOUND.getReasonPhrase());
		error.setMessage(e.getMessage());
		error.setDetails("You can't make any action on the non-existing resource");

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
