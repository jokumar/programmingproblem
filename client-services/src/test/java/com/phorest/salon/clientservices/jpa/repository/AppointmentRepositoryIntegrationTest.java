package com.phorest.salon.clientservices.jpa.repository;

import static org.junit.Assert.assertSame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppointmentRepositoryIntegrationTest {
	
	@Autowired
	 private TestEntityManager entityManager;
	
	@Autowired
	 private AppointmentRepository appointmentRepo;
	
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
	}
	 
	 @Test
	 public void testAppointmentPersistance() {
	     // given
	     Appointments appointment = new Appointments();
	     appointment.setId("e0b8ebfc-6e57-4661-9546-328c644a3764");
	     appointment.setClient(clientRepository.findById("263f67fa-ce8f-447b-98cf-317656542216").get());
	     appointment.setStart_time(LocalDateTime.parse("2016-02-07 17:15:00 +0000",dateformatter));
	     appointment.setEnd_time(LocalDateTime.parse("2016-02-07 20:15:00 +0000",dateformatter));
	     entityManager.persist(appointment);
	     entityManager.flush();
	  
	     // when
	     Appointments found = appointmentRepo.findById("e0b8ebfc-6e57-4661-9546-328c644a3764").get();
	  
	     // then
	     assertSame(found.getClient().getId(),appointment.getClient().getId());
		
		  assertSame(found.getStart_time(), appointment.getStart_time());
		  assertSame(found.getEnd_time(), appointment.getEnd_time());
		 
	 }
	 
	 @Test
	 public void testFindAllAppointMentsByDate() {
	     // given
	     Appointments appointment = new Appointments();
	     appointment.setId("e0b8ebfc-6e57-4661-9546-328c644a3764");
	     appointment.setClient(clientRepository.findById("263f67fa-ce8f-447b-98cf-317656542216").get());
	     appointment.setStart_time(LocalDateTime.parse("2016-02-07 17:15:00 +0000",dateformatter));
	     appointment.setEnd_time(LocalDateTime.parse("2016-02-07 20:15:00 +0000",dateformatter));
	     entityManager.persist(appointment);
	     entityManager.flush();
	     LocalDateTime time= LocalDateTime.parse("2016-02-01 00:00:00 +0000",dateformatter);
	     // when
	     List<Appointments> found = appointmentRepo.findAllAppointMentsByDate(time);
	    
	     assertSame(found.get(0).getId(),appointment.getId());
	    
	 }
	 

}
