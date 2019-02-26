package com.cognizant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.dao.ProductDAO;
import com.cognizant.helper.FactoryProductDAO;

/**
 * Servlet implementation class DisplayController
 */
@WebServlet("/display")
public class DisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		ProductDAO productDAO=FactoryProductDAO.createProductDAO();
		InputStream image=productDAO.displayImage(1);
		ServletOutputStream outputStream=response.getOutputStream();
		int byteValue=0;
		while((byteValue=image.read())!=-1){
			outputStream.write(byteValue);
		}
		
	}

}
