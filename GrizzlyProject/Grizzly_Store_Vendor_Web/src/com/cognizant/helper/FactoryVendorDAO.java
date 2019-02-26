package com.cognizant.helper;

import com.cognizant.dao.VendorDAO;
import com.cognizant.dao.VendorDAOImpl;

public class FactoryVendorDAO {
	public static VendorDAO createVendorDAO()
	{
		VendorDAO venderDAO=new VendorDAOImpl();
		return venderDAO;
		
	}
}
