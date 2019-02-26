package com.cognizant.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.entity.Product;
import com.cognizant.entity.Vendor;
import com.cognizant.helper.ConnectionManager;

public class ProductDAOImpl implements ProductDAO{

	private ConnectionManager manager=new ConnectionManager();
	@Override
	public boolean checkProduct(int productId, String productCategory) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		boolean result=false;
		try {
			PreparedStatement statement=connection.prepareStatement("select * from Products where product_id=? and product_category=?");
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
			PreparedStatement preparedStatement=connection.prepareStatement("insert into Products values(?,?,?,?,?)");
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setString(3,product.getProductCategory());
			preparedStatement.setString(4, product.getProductDescription());
			preparedStatement.setDouble(5, product.getProductPrice());
			int productRows=preparedStatement.executeUpdate();
			
			manager.closeConnection();
			if(productRows>0)
				result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Product> viewProducts() {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		List<Product> productList=new ArrayList<>();
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from products");
			
			while(resultSet.next()){
				Product product=new Product();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setProductCategory(resultSet.getString("product_category"));
				product.setProductDescription(resultSet.getString("product_description"));
				product.setProductPrice(resultSet.getDouble("product_price"));
				product.setBrand(resultSet.getString("brand"));
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return productList;
	}

	@Override
	public InputStream displayImage(int productId) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		InputStream image=null;
		try {
			PreparedStatement statement=connection.prepareStatement("select product_image from productimage where product_id=?");
			statement.setInt(1,productId);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
				image=resultSet.getBlob("product_image").getBinaryStream();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	public boolean addImage(byte is[],int productId){
		Connection connection=manager.openConnection();
		boolean result=false;
		try{
		Statement statement =connection.createStatement();
		
		ResultSet resultSet=statement.executeQuery("select max(image_id) from productimage");
		int imageId=0;
		while(resultSet.next()){
			imageId=resultSet.getInt(1);
		}
		int imageRows=0;
		
		
			PreparedStatement imageStatement=connection.prepareStatement("insert into ProductImage values(?,?,?)");
			imageStatement.setBytes(1, is);
			imageStatement.setInt(2,++imageId);
			imageStatement.setInt(3,productId);
			imageRows=imageStatement.executeUpdate();
			if(imageRows>0)
				result=true;
			manager.closeConnection();

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public int getProductId(String productCategory) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		int productId=0;
		try {
			PreparedStatement statement=connection.prepareStatement("select product_id from products where product_category=?");
			statement.setString(1,productCategory);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
				productId=resultSet.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		return productId;
	}

	@Override
	public boolean updateVendorQuantity(int productId, int vendorId,
			int quantity) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		boolean insertedRow=false;
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select max(sr_no) from vendor_product");
			int srNo=0;
			while(resultSet.next()){
				srNo=resultSet.getInt(1);
			}
			PreparedStatement preparedStatement=connection.prepareStatement("insert into vendor_product values(?,?,?,?)");
			preparedStatement.setInt(1,++srNo);
			preparedStatement.setInt(2,productId);
			preparedStatement.setInt(3, vendorId);
			preparedStatement.setInt(4, quantity);
			
			int rows=preparedStatement.executeUpdate();
			if(rows>0){
				insertedRow=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          manager.closeConnection();
		
		return insertedRow;
	}

	@Override
	public List<Product> getProducts(int vendorId) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		List<Product> productList=new ArrayList<>();

		try {
			PreparedStatement statement=connection.prepareStatement("select p.product_id,vp.vendor_id,vp.quantity,p.product_name,p.brand,p.product_category,p.product_description,p.product_price from vendor_product vp inner join products p on vp.product_id=p.product_id   where vendor_id=?");
			statement.setInt(1, vendorId);
			ResultSet resultSet=statement.executeQuery();
			int productId=0;
			int quantity=0;
			
			while(resultSet.next()){
				
             Product product=new Product();
             product.setProductId(resultSet.getInt("product_id"));
             product.setProductName(resultSet.getString("product_name"));
             product.setProductCategory(resultSet.getString("product_category"));
             product.setProductDescription(resultSet.getString("product_description"));
             product.setProductPrice(resultSet.getDouble("product_price"));
             product.setBrand(resultSet.getString("brand"));
             
             Vendor vendor=new Vendor();
 			 vendor.setVendorId(resultSet.getInt("vendor_id"));
 			 vendor.setQuantity(resultSet.getInt("quantity"));
 			
 			 product.setVendor(vendor);
 			
 			 productList.add(product);

			}
			
			manager.closeConnection();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	
	
}
