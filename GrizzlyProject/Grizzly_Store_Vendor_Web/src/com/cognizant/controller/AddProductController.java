package com.cognizant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.cognizant.entity.Product;
import com.cognizant.helper.FactoryProductService;
import com.cognizant.service.ProductService;

/**
 * Servlet implementation class AddProductController
 */
@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean productAdded=false;	
		int productId=0;
		String productCategory="";
		byte input[]=null;
		String productName="";
		String productDescription="";
		double productPrice=0.0;
		Product product=new Product();

		ProductService productService=FactoryProductService.createProductService();
		RequestDispatcher dispatcher=request.getRequestDispatcher("addProduct.jsp");

		boolean productExists=false;
		
		if (ServletFileUpload.isMultipartContent(request)) {
		    ServletFileUpload fileUpload = new ServletFileUpload();
		    FileItemIterator items=null;
			try {
				items = fileUpload.getItemIterator(request);
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
			
			while (items.hasNext()) {
			    FileItemStream item = items.next();
		        InputStream is = item.openStream();
			    if(item.isFormField())
			    {
			      String fieldName = item.getFieldName();
			      System.out.println(fieldName);
			      if(fieldName.equals("productId")){
			    	  System.out.println(item.toString());
			    	   byte[] str = new byte[is.available()];
		               is.read(str);
		               productId=Integer.parseInt(new String(str,"UTF8"));
			      }
			      if(fieldName.equals("productName")){
			    	  byte[] str = new byte[is.available()];
		               is.read(str);
		               productName=new String(str,"UTF8");
			      }
			      if(fieldName.equals("productCategory")){
			    	  byte[] str = new byte[is.available()];
		               is.read(str);
		               productCategory=new String(str);
			      }
			      if(fieldName.equals("productDescription")){
			    	  byte[] str = new byte[is.available()];
		               is.read(str);
		               productDescription=new String(str,"UTF8");
			      }
			      if(fieldName.equals("productPrice")){
			    	  byte[] str = new byte[is.available()];
		               is.read(str);
		               productPrice=Double.parseDouble(new String(str,"UTF8"));
			      }
			     
			      
			      
			    }
			    else{
			    	   input = new byte[is.available()];
		

			    }
			}
			}

catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
	
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductCategory(productCategory);
		product.setProductPrice(productPrice);
		
		productExists=productService.checkProduct(product.getProductId(), product.getProductCategory());
		if(!productExists){

		 productAdded=productService.addProduct(product);
		}
		else{
			
			request.setAttribute("status", "Product Id and Category already exists");
			dispatcher.forward(request,response);
		}
      
    	if(productAdded){
    	productService.addImage(input, product.getProductId());
    	}
    	else{
    		request.setAttribute("status", "Image add failed");
    	}
		
		
		if(productAdded){
			request.setAttribute("status","Product Added Successfully");
			dispatcher.forward(request,response);
		}else{
			request.setAttribute("status","Product Add Failed");
			dispatcher.forward(request,response);

		}
		
		
		
	}

 }

