package com.assessment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;

import org.junit.Test;


public class WordCountUtilityServiceTest {

	private String str1="\n\n\n=== User suspension ===\nIn April 2009, Stack Exchange implemented a policy of \"timed suspension\" Stack Exchange implemented a policy of \"timed suspension\", in order to curtail users who either show \"No effort to learn ";
	
	
	@Test
	public void testWordcount(){
		
		WordCountUtilityService service= new WordCountUtilityService();
		
		LinkedHashMap<String, Integer> map=service.wordCount(str1);
		assertEquals(new Integer(3),map.get("suspension"));
		assertEquals(new Integer(1),map.get("April"));
		assertEquals(new Integer(2),map.get("Stack"));
		assertNull(map.get("of"));
		assertNull(map.get("2009"));
		
	}
	
	@Test
	public void testFilterTopWords(){
		WordCountUtilityService service= new WordCountUtilityService();
		String str1="\n\n\n=== User suspension ===\nIn April 2009, Stack Exchange implemented a policy of \"timed suspension\" Stack Exchange implemented a policy of \"timed suspension\", in order to curtail users who either show \"No effort to learn ";
		String output=service.filterTopWords(service.wordCount(str1), 2);
		assertTrue(output.indexOf("3 suspension")>0);
		assertTrue(output.indexOf("2 Exchange,implemented")>0);
		assertFalse(output.indexOf("1 April,curtail")>0);
	}
}
