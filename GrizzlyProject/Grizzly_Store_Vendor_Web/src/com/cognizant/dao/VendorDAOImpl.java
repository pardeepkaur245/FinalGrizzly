package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognizant.helper.ConnectionManager;
import com.cognizant.helper.Status;

public class VendorDAOImpl implements  VendorDAO {
	private ConnectionManager manager= new ConnectionManager();
	boolean flag=false;
	Status  status=Status.DENY;
	
	public Status authVendor(String userName, String password, int attempt) {
		// TODO Auto-generated method stubif(attempt>3){
		status=Status.LOCK;
		if(attempt>3){
			status=Status.LOCK;
			return status;
		}
	Connection connection =manager.openConnection();
	
	try{
		PreparedStatement stmt = connection.prepareStatement("select * from VENDOR_LOGIN where USERNAME=? and PASSWORD=?");
		stmt.setString(1,userName);
		stmt.setString(2,password);
		ResultSet set= stmt.executeQuery();
		flag=set.next();
		if(!flag){
			if(attempt==2){
				status=Status.LOCK;
				PreparedStatement stmt2=connection.prepareStatement("UPDATE VENDOR_LOGIN set STATUS='L' where USERNAME=?");
				stmt2.setString(1,userName);
				stmt2.executeUpdate();
			}
			else{
				status=Status.DENY;
			}
		}
		else{
			String st=set.getString("STATUS");
			if(st.equals("L")){
				status=Status.LOCK;
			}
			else{
				status=Status.ACCEPT;
			}
		}
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	manager.closeConnection();
	return status;
		
	}
}
