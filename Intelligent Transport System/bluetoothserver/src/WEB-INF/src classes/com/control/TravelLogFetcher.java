package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class TravelLogFetcher extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String datfrm = request.getParameter("yearfrm")+"-"+request.getParameter("monthfrm")+"-"+request.getParameter("datefrm");
		String datto = request.getParameter("yearto")+"-"+request.getParameter("monthto")+"-"+request.getParameter("dateto");
		HttpSession session = request.getSession(false);
		String btadr=(String)session.getAttribute("bluetooth_adr");
		if(btadr==null) response.sendRedirect("Login.jsp");
		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();		
		try
		{
			Class.forName(sc.getInitParameter("driverclassname"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
			Statement st = con.createStatement();
			String query="select SOURCE, DESTINATION, FARE, DATE_VALUE from TRAVELERS_LOG where BLUETOOTH_ADR='"+btadr+"' and date_value>'"+datfrm+"' and date_value<='"+datto+"'";
			ResultSet res = st.executeQuery(query);
			out.println("<table>");
			out.println("<caption>Travel Log of "+(String)session.getAttribute("name")+"</caption>");
			out.println("<tr>");
				out.println("<th>"); out.println("DATE"); out.println("</th>");
				out.println("<th>"); out.println("SOURCE");	out.println("</th>");
				out.println("<th>"); out.println("DESTINATION"); out.println("</th>");
				out.println("<th>"); out.println("FARE"); out.println("</th>");
			out.println("</tr>");			
			while (res.next()) 
			{
				out.println("<tr>");
					out.println("<th>"); out.println(res.getString("date_value")); out.println("</th>");
					out.println("<td>"); out.println(res.getString("source")); out.println("</td>");
					out.println("<td>"); out.println(res.getString("destination")); out.println("</td>");
					out.println("<td>"); out.println(res.getString("fare")); out.println("</td>");
				out.println("</tr>");			
			}
			out.println("</table>");
			out.print("<a href=\""+"Account.jsp"+"\">Go Back</a>\t");
			out.println("<a href=\""+"LogOut.do"+"\">Log Out</a>");
		}catch(Exception e){System.out.println("Exception in TravelLogFetcher is "+e);}
	}
}