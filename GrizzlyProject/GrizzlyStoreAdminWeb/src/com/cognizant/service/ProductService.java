package com.cognizant.service;

import java.util.List;


import com.cognizant.controller.Product;

public interface ProductService {
	
	boolean checkProduct(int productId,String productCategory);
	boolean addProduct(Product product);
	List<Product> viewProducts();

}
