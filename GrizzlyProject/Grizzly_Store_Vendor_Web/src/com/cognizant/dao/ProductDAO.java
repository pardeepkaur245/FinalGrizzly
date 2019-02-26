package com.cognizant.dao;

import java.io.InputStream;
import java.util.List;

import com.cognizant.entity.Product;

public interface ProductDAO {
	boolean checkProduct(int productId,String productCategory);
	boolean addProduct(Product product);
	List<Product> viewProducts();
	InputStream displayImage(int productId);
	boolean addImage(byte is[],int productId);
	int getProductId(String productCategory);
	boolean updateVendorQuantity(int productId,int vendorId,int quantity);
	List<Product> getProducts(int vendorId);
}
