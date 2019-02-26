package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.Product;

public interface ProductService {
	boolean checkProduct(int productId,String productCategory);
	boolean addProduct(Product product);
	List<Product> viewProducts();
	boolean addImage(byte is[],int productId);
	boolean updateVendorQuantity(String productCategory,int vendorId,int quantity);
	List<Product> getProducts(int vendorId);
}
