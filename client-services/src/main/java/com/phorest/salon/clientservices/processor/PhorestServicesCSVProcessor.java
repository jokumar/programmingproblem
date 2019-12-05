package com.phorest.salon.clientservices.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.phorest.salon.clientservices.jpa.client.Services;
import com.phorest.salon.clientservices.jpa.repository.AppointmentRepository;
import com.phorest.salon.clientservices.jpa.repository.ServicesRepository;

@Component
public class PhorestServicesCSVProcessor implements PhorestCSVProcessor {
	
	@Autowired
	private ServicesRepository servicerepo;
	
	@Autowired
	private AppointmentRepository appointmentrepo;

	@Override
	public Boolean processCSV(MultipartFile file) {
		BufferedReader br;
		List<Services> listOfServices = new ArrayList<>();
		try {
			 int count = 0;
		     String line;
		     InputStream is = file.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     while ((line = br.readLine()) != null) {
		    	 if(count>=1) {
		    	 String[] arrayofServices = line.split(",");
		    	 Services service = new Services();
		    	 service.setId(arrayofServices[0]);
		    	 service.setAppointment(appointmentrepo.findById(arrayofServices[1]).get());
		    	 service.setName(arrayofServices[2]);
			     
		    	 service.setPrice(Double.valueOf(arrayofServices[3]));
		    	 service.setLoyalty_points(Integer.valueOf(arrayofServices[4]));
			     listOfServices.add(service);
			     
		    	 }
			     count++;
		     }
		     servicerepo.saveAll(listOfServices);
		     
		
	 } catch (IOException e) {
	    System.err.println(e.getMessage());       //TODO
	  }
		return Boolean.TRUE;
	}

}
