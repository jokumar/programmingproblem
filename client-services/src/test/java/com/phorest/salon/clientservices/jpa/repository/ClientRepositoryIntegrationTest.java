package com.phorest.salon.clientservices.jpa.repository;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.phorest.salon.clientservices.jpa.client.Client;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryIntegrationTest {
	
	@Autowired
	 private TestEntityManager entityManager;
	 
	 @Autowired
	 private ClientRepository clientRepository;
	 
	 @Test
	 public void testClientPersistance() {
	     // given
	     Client client = new Client();
	     client.setId("e0b8ebfc-6e57-4661-9546-328c644a3764");
	     client.setFirst_name("Dori");
	     client.setLast_name("Dietrich");
	     client.setBanned(Boolean.FALSE);
	     client.setEmail("patrica@keeling.net");
	     client.setGender("Male");
	     client.setPhone("(272) 301-6356");
	     entityManager.persist(client);
	     entityManager.flush();
	  
	     // when
	     Client found = clientRepository.findById("e0b8ebfc-6e57-4661-9546-328c644a3764").get();
	  
	     // then
	     assertSame(found.getFirst_name(),client.getFirst_name());
	     assertSame(found.getLast_name(), client.getLast_name());
	     assertSame(found.getBanned(), client.getBanned());
	     assertSame(found.getEmail(), client.getEmail());
	     assertSame(found.getGender(), client.getGender());
	     assertSame(found.getPhone(), client.getPhone());
	 }
	 
}
