package com.cognizant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.helper.FactoryProductService;
import com.cognizant.service.ProductService;

/**
 * Servlet implementation class ListProductServletClass
 */
@WebServlet(name = "ListProductServlet", urlPatterns = { "/listProduct" })
public class ListProductServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductService productService=FactoryProductService.createProductService();
		List<Product> productList=productService.viewProducts();
		request.setAttribute("productList",productList);
		ServletContext sc=this.getServletContext();
		System.out.println("List Product jsp called");
		RequestDispatcher dispatcher=request.getRequestDispatcher("/viewProduct.jsp");
		dispatcher.forward(request, response);
		
	}

}
