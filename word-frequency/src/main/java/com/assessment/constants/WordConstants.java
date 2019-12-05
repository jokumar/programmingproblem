package com.assessment.constants;

public class WordConstants {

	
	public static final String API = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&pageids=%d";
	public static final String JSON_API = API+"&explaintext&format=json";
	public static final String METHOD_TYPE = "GET";
	public static final String APPLICATION_TYPE = "application/json";
	public static final String QUERY = "query";
	public static final String EXTRACT = "extract";
	public static final String TITLE = "title";
	public static final String PAGES = "pages";
	
}
