package com.application.exception;

public class DetailsNotFoundException extends RuntimeException {
	public DetailsNotFoundException() {

	}

	public DetailsNotFoundException(String message) {
		super(message);
	}
}
