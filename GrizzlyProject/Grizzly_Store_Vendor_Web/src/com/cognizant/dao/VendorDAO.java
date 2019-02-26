package com.cognizant.dao;

import com.cognizant.helper.Status;

public interface VendorDAO {
	
	public 	Status authVendor(String userName,String password,int attempt);

}
