package com.phorest.salon.clientservices.exceptions;

public class PhorestCSVValidationException extends RuntimeException {
	 private String message;

	    public PhorestCSVValidationException(String message) {
	        super(message);
	        this.message = message;
	    }
}
