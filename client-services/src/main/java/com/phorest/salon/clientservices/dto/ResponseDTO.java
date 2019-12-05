package com.phorest.salon.clientservices.dto;

public class ResponseDTO {

	 public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	private String message;
	 
	 private Boolean value;
}
