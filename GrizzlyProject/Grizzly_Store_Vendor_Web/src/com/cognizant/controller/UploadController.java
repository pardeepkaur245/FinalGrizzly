package com.cognizant.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.cognizant.helper.ConnectionManager;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (ServletFileUpload.isMultipartContent(request)) {
		    ServletFileUpload fileUpload = new ServletFileUpload();
		    FileItemIterator items=null;
			try {
				items = fileUpload.getItemIterator(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    // iterate items
			
		
			ConnectionManager manager=new ConnectionManager();
			Connection connection=manager.openConnection();
		
		    try {
		    	
				while (items.hasNext()) {
				    FileItemStream item = items.next();
				    if (!item.isFormField()) {
				        InputStream is = item.openStream();
				    	try {
							PreparedStatement statement=connection.prepareStatement("insert into ProductImage values(?,?,?)");
							statement.setBlob(1, is);
							statement.setInt(2,1);
							statement.setInt(3,1);
							int rows=statement.executeUpdate();
							System.out.println("Image uploaded:"+rows);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        System.out.println(is);
				    }
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}   
	}
	}


