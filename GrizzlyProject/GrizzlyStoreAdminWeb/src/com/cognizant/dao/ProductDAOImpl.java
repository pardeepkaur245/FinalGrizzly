package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.controller.Product;
import com.cognizant.helper.ConnectionManager;

public class ProductDAOImpl implements ProductDao{

	private ConnectionManager manager=new ConnectionManager();
	@Override
	public boolean checkProduct(int productId, String productCategory) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		boolean result=false;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from ADD_PRODUCT where PRODUCT_ID=? and CATEGORY=?");
		    statement.setInt(1, productId);
		    statement.setString(2,productCategory);
		    ResultSet resultSet=statement.executeQuery();
		    while(resultSet.next()){
		    	result=true;
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		
		return result;
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		
		Connection connection=manager.openConnection();
		boolean result=false;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into ADD_PRODUCT values(?,?,?,?,?)");
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2,product.getProductCategory());
			preparedStatement.setString(3, product.getProductName());
			preparedStatement.setString(4, product.getProductDescription());
			preparedStatement.setDouble(5, product.getProductPrice());
			int rows=preparedStatement.executeUpdate();
			manager.closeConnection();
			if(rows>0)
				result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
int productId;

String productCategory;
String productName;
String productDescription;
double productPrice;

	@Override
	public List<Product> viewProducts() {
		// TODO Auto-generated method stub
		List<Product> list=new ArrayList<>();
		Product product=new Product();
		Connection connection=manager.openConnection();
		try {
			PreparedStatement statement=connection.prepareStatement("select * from ADD_PRODUCT");
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
			productId=resultSet.getInt("PRODUCT_ID");
			productCategory=resultSet.getString("CATEGORY");
			productName=resultSet.getString("NAME");
			productDescription=resultSet.getString("DESCRIPTION");
			productPrice=resultSet.getDouble("PRICE");
			
			
		
		product.setProductId(productId);
		product.setProductCategory(productCategory);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductPrice(productPrice);
		System.out.println("Product Added!!!");
		list.add(product);
			}
			System.out.println("Number of products added "+list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
