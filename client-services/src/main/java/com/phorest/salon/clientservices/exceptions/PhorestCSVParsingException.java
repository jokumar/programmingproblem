package com.phorest.salon.clientservices.exceptions;

public class PhorestCSVParsingException extends RuntimeException {
	 private String message;

	    public PhorestCSVParsingException(String message) {
	        super(message);
	        this.message = message;
	    }
}
