package com.programs.obb.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.programs.obb.OBBExecutorUtil;
import com.programs.obb.OBBLaserConstants;

public class OBBLaserDao{

	
	/**
	 * 
	 * @return
	 * 
	 * Get the JDBC connection
	 */
	public static  Connection getConnection()  {
		Connection con=null;
		try {
		
		//SQL JDBC Driver
		Class.forName(OBBLaserConstants.DRIVER_NAME);
		
		//Assign the Original DB details 
		//Username and password will go in property file . 
		con = DriverManager.getConnection(OBBLaserConstants.CONNECTION_URL,OBBLaserConstants.DB_USERNAME,OBBLaserConstants.DB_PWD);
	    
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Not able to connect to DB ");
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 
	 * @param elr
	 * @param lat
	 * @param longitude
	 * @return
	 * 
	 * Get the Mileage information 
	 */
	public BigDecimal getMileage(String elr,BigDecimal lat,BigDecimal longitude) {
		CallableStatement cstmt=null;
		BigDecimal mileage=new BigDecimal(0);
		Connection con=OBBLaserDao.getConnection();
		try {
			cstmt = (CallableStatement) con.prepareCall("{? = CALL "+OBBLaserConstants.LASER_FUNCTIONNAME+"(?,?,?)}");
		
		cstmt.registerOutParameter(1, Types.DECIMAL);
		cstmt.setString(2, elr);
		cstmt.setBigDecimal(3, lat);
		cstmt.setBigDecimal(4, longitude);
		
		cstmt.execute();
		
		 mileage = cstmt.getBigDecimal(1);
		} catch (SQLException e) {
			System.err.println("Not able to connect to DB ");
			e.printStackTrace();
		}
		 finally{
	            try{
	                if(cstmt != null) cstmt.close();
	                if(con != null) con.close();
	            } catch(Exception ex){
	            	System.err.println("Error in closing connection");
	            }
	        }
		
		return mileage;
	}
	
	/**
	 * 
	 * @param laserList
	 * @param elr
	 * @return
	 * 
	 * Update the Laser Information in LaserTable
	 */
	public boolean updateLaserTable(ArrayList<String[]> laserList,String elr){
		Connection con = OBBLaserDao.getConnection();
		Statement stmt=null;
		
		try {
			stmt=con.createStatement();
			for(String[] str:laserList){
				BigDecimal x=OBBExecutorUtil.getX(Double.valueOf(str[4]),Double.valueOf(str[5]));
				BigDecimal y=OBBExecutorUtil.getY(Double.valueOf(str[4]),Double.valueOf(str[5]));
				// Invokes the Function to calculate Mileage
			
				BigDecimal time=new BigDecimal(str[0]);
				BigDecimal lon=new BigDecimal(str[1]);
				BigDecimal lat=new BigDecimal(str[2]);
				BigDecimal height=new BigDecimal(str[3]);
				BigDecimal mileage = getMileage(elr, lat, lon);
				String query = "insert into laser values ("+time+","+lon +","+ lat +","+ height +","+x+","+y+",'"+elr+ "',"+mileage+")";
				stmt.addBatch(query);
			}
			stmt.executeBatch();
		} catch (SQLException e) {
			System.err.println("Exception in udpating laserTable");
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.err.println("Exception in closing connection");
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
