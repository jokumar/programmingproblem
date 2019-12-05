package com.assessment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author joykumar
 *
 */
public class ConsumerService {
	final static Logger LOGGER = LogManager.getLogger(ConsumerService.class);
	/**
	 * 
	 * @param page_id
	 * @param methodType
	 * @return the response in String format
	 */
	public String invokeAPI(String formattedUrl, String methodType,String applicationType) {

		try {
		
			URL url = new URL(formattedUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(methodType);
			conn.setRequestProperty("Accept",applicationType );
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder data = new StringBuilder();
			while ((output = br.readLine()) != null) {
				data.append(output);
			}
			
			return data.toString();
		} catch (IOException e) {
			LOGGER.info("Error in connecting to the URL " + e);
		}

		return null;
	}

	
}
