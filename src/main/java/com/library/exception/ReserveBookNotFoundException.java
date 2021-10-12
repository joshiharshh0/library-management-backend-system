package com.library.exception;

@SuppressWarnings("serial")
public class ReserveBookNotFoundException extends RuntimeException {

	public ReserveBookNotFoundException(Long id) {
		super("Could not find reservation with id: " + id);
	}
}
