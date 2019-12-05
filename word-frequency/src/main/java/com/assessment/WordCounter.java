package com.assessment;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.assessment.constants.WordConstants;
import com.assessment.exceptions.DataValidatorException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * 
 * @author joykumar
 *
 *         Class to count the top words
 */
public class WordCounter {

	final static Logger LOGGER = LogManager.getLogger(WordCounter.class);

	private ConsumerService consumerService;

	public void setConsumerService(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	public ConsumerService getConsumerService() {
		return this.consumerService;
	}

	/**
	 * 
	 * @param args
	 * 
	 *            main class to read input from the console .
	 */
	public static void main(String[] args) {

		try {
			Scanner reader = new Scanner(System.in);
			Long page_id = reader.nextLong();
			int top_words = reader.nextInt();
			reader.close();
			WordCounter wordCounter = new WordCounter();
			wordCounter.setConsumerService(new ConsumerService());

			String jsonText = wordCounter.getWikiExtractsById(page_id, WordConstants.METHOD_TYPE);

			WordCountUtilityService wordCountUtilityService = new WordCountUtilityService();
			LinkedHashMap<String, Integer> wordMap = wordCountUtilityService.wordCount(jsonText);

			LOGGER.info(wordCountUtilityService.filterTopWords(wordMap, top_words));

		} catch (InputMismatchException e) {
			LOGGER.error("Wrong Input", e);
		} catch (DataValidatorException e) {
			LOGGER.error("Data Validation error", e);
		}

	}

	/**
	 * 
	 * @param page_id
	 * @param methodType
	 * @return return the json extract
	 * @throws DataValidatorException
	 */
	public String getWikiExtractsById(Long page_id, String methodType) throws DataValidatorException {
		String formattedUrl = String.format(WordConstants.JSON_API, page_id);
		LOGGER.info("URL : " + formattedUrl);
		String wiki_data = getConsumerService().invokeAPI(formattedUrl, methodType, WordConstants.APPLICATION_TYPE);
		if (wiki_data == null || wiki_data.isEmpty()) {
			throw new DataValidatorException("The extracted data is empty");
		}

		String extract = getExtractedData(WordConstants.EXTRACT, wiki_data, page_id.toString());
		String title = getExtractedData(WordConstants.TITLE, wiki_data, page_id.toString());
		LOGGER.info("Title : " + title);
		String final_extract = title + " " + extract;
		return final_extract;
	}

	/**
	 * 
	 * @param key
	 * @throws DataValidatorException
	 * @returns the extracted data from the json based on key
	 */
	private String getExtractedData(String key, String wiki_data, String page_id) throws DataValidatorException {

		JsonElement jelement = new JsonParser().parse(wiki_data);
		if (jelement == null) {
			throw new JsonParseException("Could not parse the json string");
		}
		JsonObject jobject = jelement.getAsJsonObject();
		JsonElement key_data = new JsonPrimitive("");

		if (jobject == null || jobject.getAsJsonObject(WordConstants.QUERY) == null
				|| jobject.getAsJsonObject(WordConstants.QUERY).getAsJsonObject(WordConstants.PAGES) == null) {
			throw new JsonParseException("Something wrong with the json response,Try with a different page id");
		}
		JsonObject pageId_data = jobject.getAsJsonObject(WordConstants.QUERY).getAsJsonObject(WordConstants.PAGES)
				.getAsJsonObject(page_id.toString());

		if (pageId_data != null && key != null) {
			key_data = pageId_data.get(key);
		}
		if (key_data == null) {
			throw new DataValidatorException("The query data is empty for the page id " + page_id);
		}
		return key_data.getAsString();
	}

}
