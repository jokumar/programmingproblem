package com.phorest.salon.clientservices.processor;

import static org.junit.Assert.assertSame;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.phorest.salon.clientservices.jpa.client.Appointments;
import com.phorest.salon.clientservices.jpa.client.Client;
import com.phorest.salon.clientservices.jpa.repository.AppointmentRepository;
import com.phorest.salon.clientservices.jpa.repository.ClientRepository;

@RunWith(SpringRunner.class)
public class PhorestAppointmentCSVProcessorTest {
	@InjectMocks
	PhorestAppointmentCSVProcessor phorestAppointmentCSVProcessor;
	@Mock
	private AppointmentRepository appointmentrepo;
	
	@Mock
	private ClientRepository clientrepo;


	@Before
	public void setUp() {
		Client client = new Client();
		Mockito.when(clientrepo.findById(Mockito.anyString())).thenReturn(Optional.of(client));
		
		Appointments appointments = new Appointments();
		appointments.setId("7416ebc3-12ce-4000-87fb-82973722ebf4");
		List<Appointments> listOfAppointments = new ArrayList<>();
		listOfAppointments.add(appointments);
		Mockito.when(appointmentrepo.saveAll(Mockito.anyList())).thenReturn(listOfAppointments);
	}

	@Test
	public void testSuccessProcessCSV() {
		
		StringBuilder str=new StringBuilder();
		str.append("id,client_id,start_time,end_time \n")
		.append("7416ebc3-12ce-4000-87fb-82973722ebf4,263f67fa-ce8f-447b-98cf-317656542216,2016-02-07 17:15:00 +0000,2016-02-07 20:15:00 +0000\n")
		.append("e01901d2-bbdc-485f-a3ee-8d4a5e8d77fe,7f71a7f3-629b-42c2-a903-9460bf6b1891,2016-02-10 13:00:00 +0000,2016-02-10 13:45:00 +0000");
		
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				str.toString().getBytes());

		assertSame(Boolean.TRUE,phorestAppointmentCSVProcessor.processCSV(firstFile));
	}

	@Test
	public void testFailProcessCSV() {
		
		StringBuilder str=new StringBuilder();
		str.append("id,client_id,start_time,end_time \n")
		.append("7416ebc3-12ce-4000-87fb-82973722ebf4,263f67fa-ce8f-447b-98cf-317656542216,2016-02-07 17:15:00 +0000,2016-02-07 20:15:00 +0000\n")
		.append("e01901d2-bbdc-485f-a3ee-8d4a5e8d77fe,7f71a7f3-629b-42c2-a903-9460bf6b1891,2016-02-10 13:00:00 +0000,2016-02-10 13:45:00 +0000");
		
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				str.toString().getBytes());
		Mockito.when(appointmentrepo.saveAll(Mockito.anyList())).thenReturn(null);
		assertSame(Boolean.FALSE,phorestAppointmentCSVProcessor.processCSV(firstFile));
	}
	
	@Test(expected = DateTimeParseException.class)
	public void testProcessCSV() {
		
		StringBuilder str=new StringBuilder();
		str.append("id,client_id,start_time,end_time \n")
		.append("7416ebc3-12ce-4000-87fb-82973722ebf4,263f67fa-ce8f-447b-98cf-317656542216,2016/02/07 17:15:00 +0000,2016-02-07 20:15:00 +0000\n")
		.append("e01901d2-bbdc-485f-a3ee-8d4a5e8d77fe,7f71a7f3-629b-42c2-a903-9460bf6b1891,2016-02-10 13:00:00 +0000,2016-02-10 13:45:00 +0000");
		
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				str.toString().getBytes());

		phorestAppointmentCSVProcessor.processCSV(firstFile);
	}
}
