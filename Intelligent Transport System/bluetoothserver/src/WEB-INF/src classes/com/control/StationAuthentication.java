package com.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class StationAuthentication extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String passkey=request.getParameter("passkey");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=response.getWriter();
		out.println(getServletConfig().getInitParameter(passkey));
		out.close();
	}
}