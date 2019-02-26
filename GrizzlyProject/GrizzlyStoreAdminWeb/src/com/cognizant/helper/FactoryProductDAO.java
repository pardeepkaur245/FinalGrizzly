package com.cognizant.helper;

import com.cognizant.dao.ProductDAOImpl;
import com.cognizant.dao.ProductDao;

public class FactoryProductDAO {
	
	public static ProductDao createProductDAO(){
		
     ProductDao productDAO=	new ProductDAOImpl();
     return productDAO;
	}

}
