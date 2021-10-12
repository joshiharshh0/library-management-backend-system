package com.library.exception;

@SuppressWarnings("serial")
public class BorrowBookNotFoundException extends RuntimeException {

	public BorrowBookNotFoundException(Long id) {
		super("Could not find borrowed with id: " + id);
	}

	public BorrowBookNotFoundException(String message) {
		super(message);
	}
}
