package com.assessment;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * 
 * @author joykumar
 *
 * Comparator for sorting the values in map
 */
public class WordComparator implements Comparator<Entry<String, Integer>> {
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o2.getValue().compareTo(o1.getValue()); //Sort in descending order
	}
}
