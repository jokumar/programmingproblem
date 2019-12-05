package com.phorest.salon.clientservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.phorest.salon.clientservices.dto.ResponseDTO;

@ControllerAdvice
public class PhorestExceptionHandler extends ResponseEntityExceptionHandler {
	
	//Response for upload file missing 
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO dto=new ResponseDTO();
		dto.setMessage(ex.getRequestPartName()+" file is missing");
		dto.setValue(false);
		return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler({ PhorestCSVValidationException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage(ex.getMessage());
		responseDTO.setValue(false);
	    return new ResponseEntity<Object>(
	    		responseDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
