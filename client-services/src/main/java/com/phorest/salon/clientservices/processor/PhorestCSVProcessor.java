package com.phorest.salon.clientservices.processor;

import org.springframework.web.multipart.MultipartFile;

public interface PhorestCSVProcessor {

	public Boolean processCSV(MultipartFile file);
}
