package com.phorest.salon.clientservices.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phorest.salon.clientservices.constants.PhorestConstants;

@Component
public class PhorestCSVProcessorFactory {
	
	@Autowired
	private PhorestClientCSVProcessor phorestClientCSVProcessor;
	
	@Autowired
	private PhorestAppointmentCSVProcessor phorestappointmentCSVProcessor;
	
	@Autowired
	private PhorestServicesCSVProcessor phorestServicesCSVProcessor;
	
	@Autowired
	private PhorestPurchasesCSVProcessor phorestPurchasesCSVProcessor;
	
	public PhorestCSVProcessor getProcessor(String fileType) {
		if(PhorestConstants.CLIENT.equals(fileType)) {
			return phorestClientCSVProcessor;
		}
		else if(PhorestConstants.APPOINTMENT.equals(fileType)) {
			return phorestappointmentCSVProcessor;
		}
		else if(PhorestConstants.SERVICE.equals(fileType)) {
			return phorestServicesCSVProcessor;
		}
		else if(PhorestConstants.PURCHASE.equals(fileType)) { 
			return phorestPurchasesCSVProcessor;
		}
		return null;
	}

}
