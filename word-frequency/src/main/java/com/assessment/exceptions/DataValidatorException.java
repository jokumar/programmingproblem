package com.assessment.exceptions;
/**
 * 
 * @author joykumar
 *	Custom exception class for data validation
 */
@SuppressWarnings("serial")
public class DataValidatorException extends Exception { 
	public DataValidatorException(String error){
		super(error);
	}
}
