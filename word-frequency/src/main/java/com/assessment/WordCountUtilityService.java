package com.assessment;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * @author joykumar Utility class to count the number of Words
 */
public class WordCountUtilityService {

	public LinkedHashMap<String, Integer> wordCount(String str) {
		String word = "";
		Boolean dlmtr = false;
		TreeMap<String, Integer> wordMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		String delimeters = "tnrf";
		for (int index = 0; index < str.length(); index++) {
			// check if the character is a letter
			if (Character.isLetter(str.charAt(index))) {
				// check delimteter \t\n\r\f ,if the dlmtr is true and the
				// character is one of delimeters ,
				// then it is not a word
				if (dlmtr && delimeters.indexOf(str.charAt(index)) > 0) {
					dlmtr = false;
					continue;
				}
				word = word + str.charAt(index);
			} else if (str.charAt(index) == '\\') {
				// check if there is a delimeter present, and set the flag to
				// true
				dlmtr = true;
			} else if (word != "") {
				// Treat the word only if its length is 4 and above
				if (word.length() >= 4) {
					if (wordMap.get(word) != null) {
						wordMap.put(word, wordMap.get(word) + 1);
					} else {
						wordMap.put(word, 1);
					}
				}
				word = "";
			}

		}
		// Sort the wordMap in descending order
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(wordMap.entrySet());
		Collections.sort(list, new WordComparator());
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {

			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	/**
	 * 
	 * @param linkedWordMap
	 * @param top_words
	 * @return filter the top words
	 */
	public String filterTopWords(LinkedHashMap<String, Integer> linkedWordMap, int top_words) {
		Integer prev_Value = -1;
		StringBuilder outputString = new StringBuilder(String.format("Top %d words:", top_words));
		int counter = 0;

		for (Map.Entry<String, Integer> entry : linkedWordMap.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			if (value != prev_Value) {
				prev_Value = value;
				counter++;
				if (counter > top_words) {
					break;
				}
				outputString.append("\n").append("- ").append(value).append(" ").append(key);

			} else {
				outputString.append(",").append(key);
			}
		}
		return outputString.toString();
	}

}
