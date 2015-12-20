package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.model.*;


public class CustomerVerifier extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		ArrayList<String> customers=new ArrayList<String>();
		
		String station=request.getParameter("station");
		response.setStatus(HttpServletResponse.SC_OK);
		Enumeration<String> paramnames=request.getParameterNames();
		while(paramnames.hasMoreElements())
		{
			String s=paramnames.nextElement();
			if(!s.equalsIgnoreCase("station")) customers.add(request.getParameter(s));
		}
		int minBalance = Integer.parseInt(getServletConfig().getInitParameter("minbalance"));
		ServletConfig scfg = getServletConfig();
		//----------------MAKING WORK WITH DATABASEWORKER--------------------
		DataBaseWorker dbw = new DataBaseWorker(station,scfg,minBalance);
		dbw.initialize();
		ArrayList<String> defaulters=null;
		try
		{
			defaulters = dbw.authorizeCustomers(customers);
		}catch(Exception e){System.out.println("Exception caught in cus_verifier "+e);}
		
		PrintWriter out=response.getWriter();
		for(String s: defaulters) out.println(s);
		out.close();
	}
}