package com.cognizant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.dao.VendorDAO;
import com.cognizant.helper.FactoryVendorDAO;
import com.cognizant.helper.Status;

/**
 * Servlet implementation class LoginControllerServletClass
 */
@WebServlet(name = "LoginControllerServlet", urlPatterns = { "/login" })
public class LoginControllerServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static int attempt=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String username= request.getParameter("userName");
		String password= request.getParameter("passWord");
		VendorDAO vendorDAO=FactoryVendorDAO.createVendorDAO();
		RequestDispatcher rd= request.getRequestDispatcher("addProduct.jsp");
		Status result = vendorDAO.authVendor(username, password,attempt);
	//	System.out.println(result);
		if(result.equals(Status.ACCEPT)){
			rd.forward(request, response);
		}
		else if(result.equals(Status.DENY))
		{
			attempt=attempt+1;
			response.sendRedirect("index.html");
		}
		else 
		{
			response.sendRedirect("lock.html");
		}
	}

	}


