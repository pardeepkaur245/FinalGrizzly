package com.cognizant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.entity.Product;
import com.cognizant.helper.FactoryProductService;
import com.cognizant.service.ProductService;

/**
 * Servlet implementation class ViewProductByVendorController
 */
@WebServlet("/viewProductByVendor")
public class ViewProductByVendorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProductByVendorController() {
        super();
        // TODO Auto-generated constructor stub4
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int vendorId=Integer.parseInt(request.getParameter("vendorId"));
		ProductService productService=FactoryProductService.createProductService();
		List<Product> productList=productService.getProducts(vendorId);
		request.setAttribute("productList", productList);
		RequestDispatcher dispatcher=request.getRequestDispatcher("viewProductVendor.jsp");
		dispatcher.forward(request, response);
	}

}
