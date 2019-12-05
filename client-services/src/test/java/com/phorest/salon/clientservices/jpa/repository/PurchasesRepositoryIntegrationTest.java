package com.phorest.salon.clientservices.jpa.repository;

import static org.junit.Assert.assertSame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.phorest.salon.clientservices.constants.PhorestConstants;
import com.phorest.salon.clientservices.jpa.client.Appointments;
import com.phorest.salon.clientservices.jpa.client.Client;
import com.phorest.salon.clientservices.jpa.client.Purchases;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchasesRepositoryIntegrationTest {
	
	@Autowired
	 private TestEntityManager entityManager;
	
	@Autowired
	 private AppointmentRepository appointmentRepo;
	
	@Autowired
	private PurchasesRepository purchasesRepo;
	
	@Autowired
	 private ClientRepository clientRepository;
	
	
	private DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern(PhorestConstants.DATEFORMAT);
	
	 @Before
	public void setup() {
		 Client client = new Client();
	     client.setId("263f67fa-ce8f-447b-98cf-317656542216");
	     client.setFirst_name("Dori");
	     client.setLast_name("Dietrich");
	     client.setBanned(Boolean.FALSE);
	     client.setEmail("patrica@keeling.net");
	     client.setGender("Male");
	     client.setPhone("(272) 301-6356");
	     entityManager.persist(client);
	     
	     Appointments appointment = new Appointments();
	     appointment.setId("7416ebc3-12ce-4000-87fb-82973722ebf4");
	     appointment.setClient(clientRepository.findById("263f67fa-ce8f-447b-98cf-317656542216").get());
	     appointment.setStart_time(LocalDateTime.parse("2016-02-07 17:15:00 +0000",dateformatter));
	     appointment.setEnd_time(LocalDateTime.parse("2016-02-07 20:15:00 +0000",dateformatter));
	     entityManager.persist(appointment);
	     
	}
	 
	 @Test
	 public void testPurchasePersistance() {
	     // given
	     Purchases purchases = new Purchases();
	     purchases.setId("d2d3b92d-f9b5-48c5-bf31-88c28e3b73ac");
	     purchases.setAppointment(appointmentRepo.findById("7416ebc3-12ce-4000-87fb-82973722ebf4").get());
	     purchases.setName("Shampoo");
	     purchases.setPrice(19.5);
	     purchases.setLoyalty_points(20);
	     entityManager.persist(purchases);
	     entityManager.flush();
	  
	     // when
	     Purchases found = purchasesRepo.findById("d2d3b92d-f9b5-48c5-bf31-88c28e3b73ac").get();
	  
	     // then
	     assertSame(found.getAppointment().getId(),purchases.getAppointment().getId());
		
		  assertSame(found.getName(), purchases.getName());
		  assertSame(found.getPrice(), purchases.getPrice());
		 
	 }
	 
	

}
