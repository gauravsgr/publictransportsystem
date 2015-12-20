package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.model.*;


public class CustomerCreater extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{doPost(request,response);}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		UserInfo n = new UserInfo();
		if(n.createUser(request,this.getServletContext())) response.sendRedirect("AddCustomer.jsp");
		else 
		{
			PrintWriter out = response.getWriter();
			out.println("User Couldnt be created Successfully");
			out.println("<a href='AddCustomer.jsp'>click here to CREATE A NEW CUSTOMER</a>");
		}
	}
}