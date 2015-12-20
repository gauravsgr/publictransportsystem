package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class Recharge extends HttpServlet
{
	ServletContext sc;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		sc = getServletContext();
		
		Enumeration<String> paramnames=request.getParameterNames();
		while(paramnames.hasMoreElements())
		{
			String s=paramnames.nextElement();
			
			try{
				if(s.equalsIgnoreCase("email")) emailRecharge(request.getParameter(s), request, response);
				else if(s.equalsIgnoreCase("emei")) emeiRecharge(request.getParameter(s), request, response);
				else if(s.equalsIgnoreCase("bluetooth_adr")) bluetoothRecharge(request.getParameter(s), request, response);
			}catch(Exception e){System.out.println("Exception in Recharge "+e);}
		}
	}
		
	public void emailRecharge(String email, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Class.forName(sc.getInitParameter("driverclassname"));
		Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
		Statement st = con.createStatement();
		String query="select BLUETOOTH_ADR from USERS_INFO where EMAIL_ID='"+email+"'";
		ResultSet res = st.executeQuery(query);
		String btadr="";
		while (res.next())
		{
			btadr = res.getString("bluetooth_adr");
			System.out.println("Bluetooth Address is "+btadr);
		}
		if(btadr.equals(""))
		{
			PrintWriter out = response.getWriter();
			out.println("No entry found with this Email. Please confirm it!");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}	
		else
		{
			st=con.createStatement();
			query="select BALANCE from REGISTERED_USERS where BLUETOOTH_ADR='"+btadr+"'";
			res = st.executeQuery(query);
			String balance="";
			while(res.next())
			{
				Integer bal = Integer.parseInt(request.getParameter("amount"))+Integer.parseInt(res.getString("balance"));
				balance=bal.toString();
			}
			st=con.createStatement();
			query="update REGISTERED_USERS set BALANCE='"+balance+"' where BLUETOOTH_ADR='"+btadr+"'";
			st.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("Account Updated Successfully");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}
	}
	
	public void emeiRecharge(String emei, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Class.forName(sc.getInitParameter("driverclassname"));
		Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
		Statement st = con.createStatement();
		String query="select BALANCE from REGISTERED_USERS where EMEI='"+emei+"'";
		ResultSet res = st.executeQuery(query);
		String balance="";
		while (res.next())
		{
			balance = res.getString("balance");
		}
		if(balance.equals(""))
		{
			PrintWriter out = response.getWriter();
			out.println("No entry found with this Imei No. Please confirm it!");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}	
		else
		{
			System.out.println("Balance is "+balance);
			Integer bal = Integer.parseInt(request.getParameter("amount"))+Integer.parseInt(balance);
			balance=bal.toString();
			st=con.createStatement();
			query="update REGISTERED_USERS set BALANCE='"+balance+"' where EMEI='"+emei+"'";
			st.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("Account Updated Successfully");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}
	}
	
	public void bluetoothRecharge(String bluetooth_adr, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Class.forName(sc.getInitParameter("driverclassname"));
		Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
		Statement st = con.createStatement();
		String query="select BALANCE from REGISTERED_USERS where BLUETOOTH_ADR='"+bluetooth_adr+"'";
		ResultSet res = st.executeQuery(query);
		String balance="";
		while (res.next())
		{
			balance = res.getString("balance");
		}
		if(balance.equals(""))
		{
			PrintWriter out = response.getWriter();
			out.println("No entry found with this Bluetooth No. Please confirm it!");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}	
		else
		{
		
			Integer bal = Integer.parseInt(request.getParameter("amount"))+Integer.parseInt(balance);
			balance=bal.toString();
			st=con.createStatement();
			query="update REGISTERED_USERS set BALANCE='"+balance+"' where BLUETOOTH_ADR='"+bluetooth_adr+"'";
			st.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("Account Updated Successfully");
			out.println("<a href=\""+"recharge.jsp"+"\">Back to Recharge Page</a>");
			out.close();
		}
	}	
}