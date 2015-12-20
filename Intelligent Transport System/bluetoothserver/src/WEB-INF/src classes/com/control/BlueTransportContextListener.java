package com.control;
import javax.servlet.*;
import com.model.*;

public class BlueTransportContextListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent event){
		ServletContext sc=event.getServletContext();		
		CreateDataStorage cds = new CreateDataStorage(sc.getInitParameter("dburl"), sc.getInitParameter("dbname"), sc.getInitParameter("dbusername"), sc.getInitParameter("dbpassword"), sc.getInitParameter("driverclassname"));
		cds.initialize();
	}
	
	public void contextDestroyed(ServletContextEvent event){
		//just nothing to do right up here
	}
}