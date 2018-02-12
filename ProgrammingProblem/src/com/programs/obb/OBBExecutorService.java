package com.programs.obb;

import java.util.ArrayList;

import com.programs.obb.dao.OBBLaserDao;

public class OBBExecutorService {

	public static void main(String[] args) {
		OBBLaserDao dao = new OBBLaserDao();
		if (args.length == 0) {
			System.err.println("Argument is null");
		}

		String csvFile = args[0];// Pass the csv file name
		String elr = "";

		if (csvFile != null) {

			String[] fileName = csvFile.split("-");
			if (fileName.length > 1) {

				elr = fileName[1];
			}
		}

		// Read the csv file and store it in Arraylist
		ArrayList<String[]> laserList = OBBExecutorUtil.readfile(csvFile);
		
		//update laserTable
		dao.updateLaserTable(laserList, elr);
	}
}
