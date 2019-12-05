package com.assessment;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.mockito.Mockito;

import com.assessment.exceptions.DataValidatorException;
import com.google.gson.JsonParseException;

public class WordCounterTest {

	@Test
	public void testGetWikiExtractsById() throws DataValidatorException {
		WordCounter counter = new WordCounter();
		ConsumerService consumerService = new ConsumerService();
		counter.setConsumerService(consumerService);
		assertFalse(counter.getWikiExtractsById(new Long(21721040), "GET").isEmpty());

	}

	@Test(expected = DataValidatorException.class)
	public void testGetWikiExtractsById_DataValidator() throws DataValidatorException {
		WordCounter counter = new WordCounter();
		ConsumerService consumerService = Mockito.mock(ConsumerService.class);
		counter.setConsumerService(consumerService);
		Mockito.when(consumerService.invokeAPI(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(null);

		counter.getWikiExtractsById(new Long(21721040), null);
	}

	@Test(expected = DataValidatorException.class)
	public void testGetWikiExtractsByWrongId_DataValidator() throws DataValidatorException {
		WordCounter counter = new WordCounter();
		ConsumerService consumerService = new ConsumerService();
		counter.setConsumerService(consumerService);
		counter.getWikiExtractsById(new Long(-1), "GET");
	}

	@Test(expected = JsonParseException.class)
	public void testGetWikiId_Json_Parse_Error() throws DataValidatorException {
		WordCounter counter = new WordCounter();
		ConsumerService consumerService = Mockito.mock(ConsumerService.class);
		counter.setConsumerService(consumerService);
		Mockito.when(consumerService.invokeAPI(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn("Not a JSON Structure");
		counter.getWikiExtractsById(new Long(-1), "GET");
	}
	
}
