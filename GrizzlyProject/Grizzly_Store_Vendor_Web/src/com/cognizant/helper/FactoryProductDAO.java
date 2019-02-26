package com.cognizant.helper;

import com.cognizant.dao.ProductDAO;
import com.cognizant.dao.ProductDAOImpl;

public class FactoryProductDAO {
	public static ProductDAO createProductDAO(){
		
	     ProductDAO productDAO=	new ProductDAOImpl();
	     return productDAO;
}
}
