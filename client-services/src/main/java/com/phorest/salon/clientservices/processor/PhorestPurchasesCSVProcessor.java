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

import com.phorest.salon.clientservices.jpa.client.Purchases;
import com.phorest.salon.clientservices.jpa.repository.AppointmentRepository;
import com.phorest.salon.clientservices.jpa.repository.PurchasesRepository;

@Component
public class PhorestPurchasesCSVProcessor implements PhorestCSVProcessor {

	@Autowired
	private PurchasesRepository purchaserepo;
	
	@Autowired
	private AppointmentRepository appointmentrepo;

	@Override
	public Boolean processCSV(MultipartFile file) {
		BufferedReader br;
		List<Purchases> listOfPurchases = new ArrayList<>();
		try {
			 int count = 0;
		     String line;
		     InputStream is = file.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     while ((line = br.readLine()) != null) {
		    	 if(count>=1) {
		    	 String[] arrayofPurchases = line.split(",");
		    	 Purchases purchase = new Purchases();
		    	 purchase.setId(arrayofPurchases[0]);
		    	 purchase.setAppointment(appointmentrepo.findById(arrayofPurchases[1]).get());
		    	 purchase.setName(arrayofPurchases[2]);
			     
		    	 purchase.setPrice(Double.valueOf(arrayofPurchases[3]));
		    	 purchase.setLoyalty_points(Integer.valueOf(arrayofPurchases[4]));
		    	 listOfPurchases.add(purchase);
			     
		    	 }
			     count++;
		     }
		     purchaserepo.saveAll(listOfPurchases);
		     
		
	 } catch (IOException e) {
	    System.err.println(e.getMessage());       //TODO
	  }
		return Boolean.TRUE;
	}
}
