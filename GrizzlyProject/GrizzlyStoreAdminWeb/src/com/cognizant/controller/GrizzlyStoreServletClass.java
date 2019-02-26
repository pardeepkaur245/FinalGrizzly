package com.cognizant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GrizzlyStoreServletClass
 */
@WebServlet(name = "GrizzlyStoreServlet", urlPatterns = { "/grizzlystore" })
public class GrizzlyStoreServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrizzlyStoreServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }
    static int count;
  
    //private Connection connection=null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static String user;
    
    int hit=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		user=request.getParameter("username");
		String pass=request.getParameter("password");
		PrintWriter out=response.getWriter();
		
		 
		if(ValidateUserClass.validateUser(user, pass))
		{
			RequestDispatcher dispatch=request.getRequestDispatcher("AddProduct.jsp");
			dispatch.forward(request, response);
			
		}
		else
		{
		count++;
		System.out.println(count);
		if(count<3){
			RequestDispatcher dispatch=request.getRequestDispatcher("Login.jsp");
			dispatch.forward(request, response);
		}else if(count==3)
		{
			ValidateUserClass.lock(user);
			RequestDispatcher dispatch=request.getRequestDispatcher("Login.jsp");
			request.setAttribute("status", "Account locked");
			dispatch.forward(request, response);
		
		}
			
		}
		
		
		
		
	}

}

