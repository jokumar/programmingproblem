package com.phorest.salon.clientservices.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.phorest.salon.clientservices.processor.PhorestAppointmentCSVProcessor;
import com.phorest.salon.clientservices.processor.PhorestCSVProcessorFactory;
import com.phorest.salon.clientservices.processor.PhorestClientCSVProcessor;
import com.phorest.salon.clientservices.processor.PhorestPurchasesCSVProcessor;
import com.phorest.salon.clientservices.processor.PhorestServicesCSVProcessor;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PhorestClientControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private PhorestClientCSVProcessor phorestClientCSVProcessor;
	@MockBean
	private PhorestAppointmentCSVProcessor phorestappointmentCSVProcessor;
	@MockBean
	private PhorestServicesCSVProcessor phorestServicesCSVProcessor;
	@MockBean
	private PhorestPurchasesCSVProcessor phorestPurchasesCSVProcessor;
	
	@Autowired
	private PhorestCSVProcessorFactory phorestCSVProcessorFactory;
	
	@Test
	public void importCSVSuccess()
	  throws Exception {
	     
		StringBuilder str=new StringBuilder();
		str.append("id,first_name,last_name,email,phone,gender,banned \n")
		.append("e0b8ebfc-6e57-4661-9546-328c644a3764,Dori,Dietrich,patrica@keeling.net,(272) 301-6356,Male,false \n")
		.append("104fdf33-c8a2-4f1c-b371-3e9c2facdfa0,Gordon,Hammes,glen@cummerata.co,403-844-1643,Male,false");
		
		MockMultipartFile clientsFile = new MockMultipartFile("clients_csv", "clients.csv", "text/plain",
				str.toString().getBytes());
		
		

		MockMultipartFile appointmentsFile = new MockMultipartFile("appointment_csv", "appointments.csv", "text/plain",
				str.toString().getBytes());
		

		MockMultipartFile servicesFile = new MockMultipartFile("services_csv", "services.csv", "text/plain",
				str.toString().getBytes());
		

		MockMultipartFile purchasesFile = new MockMultipartFile("purchases_csv", "purchases.csv", "text/plain",
				str.toString().getBytes());
	 
	    mvc.perform(MockMvcRequestBuilders.multipart("/phorest/importcsv")
                .file(clientsFile)
                .file(appointmentsFile)
                .file(servicesFile)
                .file(purchasesFile))
	    .andExpect(status().isOk());
	   //TODO
	      
	}
}