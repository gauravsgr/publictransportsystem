package com.model;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class UserInfo
{
	public boolean createUser(HttpServletRequest request, ServletContext sc)
	{
		try{
			Class.forName(sc.getInitParameter("driverclassname"));
			Connection con = DriverManager.getConnection(sc.getInitParameter("dburl")+sc.getInitParameter("dbname"),sc.getInitParameter("dbusername"),sc.getInitParameter("dbpassword"));
			//----Inserting in REGISTERED_USERS----------
			Statement st = con.createStatement();
			int val = st.executeUpdate("INSERT registered_users VALUES('"+request.getParameter("btadr")+"' ,'"+request.getParameter("name")+
			"' ,'"+request.getParameter("Emei")+"' ,'"+request.getParameter("date")+"-"+request.getParameter("month")+"-"+request.getParameter("year")
			+"' ,'"+request.getParameter("cell")+"' ,'"+"false"+"' ,"+Integer.parseInt(request.getParameter("balance"))+")");
			
			//----Inserting in USERS_INFO----------------
			st = con.createStatement();
			val = st.executeUpdate("INSERT users_info VALUES('"+request.getParameter("btadr")+"' ,'"+request.getParameter("Emei")+"' ,'"+
			request.getParameter("btadr")+"' ,'"+request.getParameter("name")+"' ,'"+request.getParameter("email")+"' ,'"+request.getParameter("gender")+"' ,'"+
			request.getParameter("street")+"' ,'"+request.getParameter("city")+"' ,'"+request.getParameter("state")+"' ,'"+
			request.getParameter("country")+"' ,'"+request.getParameter("zip")+"')");
		}
		catch (Exception s){ System.out.println("SQL statement is not executed at 34! "+s); return false;}
		final HttpServletRequest req=request;
		new Thread(new Runnable()
				{
					public void run()
					{
						try
						{
							System.out.println(req.getParameter("email")+" "+ req.getParameter("name")+" "+req.getParameter("Emei")+" "+req.getParameter("btadr"));
							AccountEmail.sendEmail(req.getParameter("email"), req.getParameter("name"),req.getParameter("Emei"),req.getParameter("btadr"));
						}catch(Exception e){System.out.println("Exception in sending the Mail "+e);}
					}
				}).start();
		return true;
	}
}
		