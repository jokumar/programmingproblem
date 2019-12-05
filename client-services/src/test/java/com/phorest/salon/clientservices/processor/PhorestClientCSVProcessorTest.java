package com.phorest.salon.clientservices.processor;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.phorest.salon.clientservices.jpa.client.Client;
import com.phorest.salon.clientservices.jpa.repository.ClientRepository;

@RunWith(SpringRunner.class)
public class PhorestClientCSVProcessorTest {
	@InjectMocks
	PhorestClientCSVProcessor phorestClientCSVProcessor;
	@Mock
	private ClientRepository clientrepo;

	@Before
	public void setUp() {
		Client client = new Client();
		List<Client> listClient = new ArrayList<>();
		listClient.add(client);
		Mockito.when(clientrepo.saveAll(listClient)).thenReturn(listClient);
	}

	@Test
	public void testSuccessProcessCSV() {
		
		StringBuilder str=new StringBuilder();
		str.append("id,first_name,last_name,email,phone,gender,banned \n")
		.append("e0b8ebfc-6e57-4661-9546-328c644a3764,Dori,Dietrich,patrica@keeling.net,(272) 301-6356,Male,false \n")
		.append("104fdf33-c8a2-4f1c-b371-3e9c2facdfa0,Gordon,Hammes,glen@cummerata.co,403-844-1643,Male,false");
		
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				str.toString().getBytes());

		assertSame(Boolean.TRUE,phorestClientCSVProcessor.processCSV(firstFile));
	}

}
