package com.cognizant.dao;

import java.util.List;

import com.cognizant.controller.Product;

public interface ProductDao {
	
	boolean checkProduct(int productId,String productCategory);
	boolean addProduct(Product product);
	List<Product> viewProducts();

}
