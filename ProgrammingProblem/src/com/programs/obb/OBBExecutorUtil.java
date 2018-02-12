package com.programs.obb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


public class OBBExecutorUtil {
	/**
	 * 
	 * @param csvFile
	 * @return
	 * Read the CSV file 
	 */
	public static ArrayList<String[]>  readfile(String csvFile){
		 ArrayList<String[]> laserList =new ArrayList<>();
		 
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			 String line = "";
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] laserInfo = line.split(",");
	                laserList.add(laserInfo);
	             
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 return laserList;
	}
	/**
	 * 
	 * @param angle
	 * @param range
	 * @return
	 * Get the Y coordinate
	 */
	public static BigDecimal  getY(double angle , double range){
		
		return new BigDecimal(Math.sin(angle)*range); // Sin (angle) = opp of angle / hypotenuse
	
	}
	
	/**
	 * 
	 * @param angle
	 * @param range
	 * @return
	 * 
	 * Get the x coordinate
	 */
	public static BigDecimal getX(double angle , double range){
		return new BigDecimal(Math.cos(angle)*range); // Cos (angle) = base of angle / hypotenuse
	}
}
