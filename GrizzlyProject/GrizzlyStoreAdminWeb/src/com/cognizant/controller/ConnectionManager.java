package com.cognizant.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
public static Connection connection=null;
	
	public static Connection openConnection()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","cognizant");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
