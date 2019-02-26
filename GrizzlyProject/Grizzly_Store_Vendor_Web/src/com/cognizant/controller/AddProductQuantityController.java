package com.cognizant.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.helper.FactoryProductService;
import com.cognizant.service.ProductService;

/**
 * Servlet implementation class AddProductQuantityController
 */
@WebServlet("/addProductQuantity")
public class AddProductQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductQuantityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession(false);
		
		PrintWriter out=response.getWriter();
		String rowCounts[]=request.getParameterValues("productSelect");
		
		int rowCount=Integer.parseInt(rowCounts[0]);
		String productName=request.getParameter("productName"+rowCount);
		String productBrand=request.getParameter("productBrand"+rowCount);
		String productCategory=request.getParameter("productCategory"+rowCount);
		int vendorId=Integer.parseInt(request.getParameter("vendorId"+rowCount));
		int quantity=Integer.parseInt(request.getParameter("quantity"+rowCount));
		
		
		ProductService productService=FactoryProductService.createProductService();
		boolean productQuantityUpdated=productService.updateVendorQuantity(productCategory, vendorId, quantity);
		RequestDispatcher dispatcher=request.getRequestDispatcher("viewProducts.jsp");
		if(productQuantityUpdated){
			request.setAttribute("status","Vendor Id and Quantity successfully updated");
			request.setAttribute("productList",session.getAttribute("productList"));
			dispatcher.forward(request,response);
		}else{
			request.setAttribute("status","Vendor Id and Quantity updation failed");
			request.setAttribute("productList",session.getAttribute("productList"));
			dispatcher.forward(request,response);

		}
		
		

		

		
	}

}
