package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginUser extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ServletContext sc = getServletContext();
		try
		{
			Class.forName(sc.getInitParameter("driverclassname"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
			Statement st = con.createStatement();
			String query="select BLUETOOTH_ADR, NAME from USERS_INFO where USERNAME='"+username+"' and PASSWORD='"+password+"'";
			ResultSet res = st.executeQuery(query);
			String btadr="";
			String name="";
			String balance="";
			while (res.next())
			{
				btadr = res.getString("bluetooth_adr");
				name = res.getString("name");
			}
			if(btadr.equals(""))
			{
				PrintWriter out = response.getWriter();
				out.println("Please Check your username and password again");
				out.println("<a href=\""+"Login.jsp"+"\">Click here to go back</a>");
				out.close();
				return;
			}
			st = con.createStatement();
			query="select BALANCE from REGISTERED_USERS where BLUETOOTH_ADR='"+btadr+"'";
			res = st.executeQuery(query);
			while (res.next()) balance = res.getString("balance");

			System.out.println("The Balance is: "+balance);
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			session.setAttribute("bluetooth_adr",btadr);
			session.setAttribute("name",name);
			session.setAttribute("balance",balance);
			response.sendRedirect("Account.jsp");
		}catch(Exception e){System.out.println("This is caugth in Login User "+e);}
	}
}

			