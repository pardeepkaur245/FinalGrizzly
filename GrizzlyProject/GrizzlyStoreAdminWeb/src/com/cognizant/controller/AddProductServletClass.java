package com.cognizant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cognizant.helper.FactoryProductService;
import com.cognizant.service.ProductService;

/**
 * Servlet implementation class AddProductController
 */
@WebServlet("/addProduct")
public class AddProductServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int productId=Integer.parseInt(request.getParameter("productId"));
		
		String productCategory=request.getParameter("productCategory");
		String productName=request.getParameter("productName");
		String productDescription=request.getParameter("productDescription");
		double productPrice=Double.parseDouble(request.getParameter("productPrice"));
		
		Product product=new Product();
		product.setProductId(productId);
		
		product.setProductCategory(productCategory);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductPrice(productPrice);
		
		
		ProductService productService=FactoryProductService.createProductService();
		boolean productExists=productService.checkProduct(productId, productCategory);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("AddProduct.jsp");
		if(!productExists){
			
			boolean productAdded=productService.addProduct(product);
			if(productAdded){
				request.setAttribute("status","Product Added Successfully");
				//RequestDispatcher dispatcher1=request.getRequestDispatcher("listProduct.jsp");
				dispatcher.forward(request,response);
			}else{
				request.setAttribute("status","Product Add Failed");
				dispatcher.forward(request,response);

			}
		}
		
		else{
			
			request.setAttribute("status", "Product Id and Category already exists");
			dispatcher.forward(request,response);
		}
		
	}

}
