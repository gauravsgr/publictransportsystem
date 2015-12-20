package com.model;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DataBaseWorker
{
	ServletConfig scnfg;
	ServletContext sc;
	Connection con;
	PreparedStatement selBal, checkSource, setStation, updatBal,insertLog;
	String station;
	int minBalance;
	
	public DataBaseWorker(String station_sender, ServletConfig servconfg, int min)
	{
		scnfg=servconfg;
		station=station_sender;
		minBalance=min;
		ServletContext sc = scnfg.getServletContext();
		try
		{
			String driverclassname = sc.getInitParameter("driverclassname");
			String dburl = sc.getInitParameter("dburl");
			String dbusername = sc.getInitParameter("dbusername");
			String dbpassword = sc.getInitParameter("dbpassword");
			String database = sc.getInitParameter("dbname");
			Class.forName(driverclassname);
			con = DriverManager.getConnection(dburl+database,dbusername,dbpassword);
		}
		catch (Exception s)
		{	
			System.out.println("SQL statement is not executed at 34! "+s);
		}
	}
  
	public void initialize()
	{
		String authorize = "SELECT balance,name,emei FROM registered_users WHERE bluetooth_adr = ?";
		String getSource = "SELECT source_id,cell_no FROM registered_users WHERE bluetooth_adr = ?";
		String setSource = "UPDATE registered_users SET source_id = ? WHERE bluetooth_adr = ?";
		String updateBalance = "UPDATE registered_users SET balance = ?, source_id = ? WHERE bluetooth_adr = ?";
		String publishLog = "INSERT travelers_log VALUES(?,?,?,?,?)";
		try
		{
			selBal = con.prepareStatement(authorize);
			checkSource = con.prepareStatement(getSource);
			setStation = con.prepareStatement(setSource);
			updatBal = con.prepareStatement(updateBalance);
			insertLog = con.prepareStatement(publishLog);
		}catch(SQLException s){System.out.println("SQL exception in preparing statements "+s);}
	}
	
	public ArrayList<String> authorizeCustomers(ArrayList<String> customers) throws Exception
	{
		ArrayList<String> cheats = new ArrayList<String>();
		ArrayList<String> lowBal = new ArrayList<String>();
		ArrayList<String> defaulters = new ArrayList<String>();
		for(String s: customers)
		{
			selBal.setString(1,s);
			ResultSet rs = selBal.executeQuery();
			int count=0;
			while (rs.next()) 
			{
				++count;
				int bal = rs.getInt("balance");
				if(bal < minBalance)
				{
					String emei=rs.getString("emei");
					String name=rs.getString("name");
					String lbc="BT: "+s+" EMEI: "+emei+"  "+name+" BALANCE: "+bal;
					lowBal.add(lbc);
					continue;
				}
				checkFare(s,bal);
				//updatBal.setInt(1,bal);
			}
			if(count==0) cheats.add(s);
		}
		if((cheats.size()==lowBal.size())&(cheats.size()==0)) defaulters.add("passed");
		else 
		{
			defaulters.add("lowbalancelist");
			for(String s: lowBal) defaulters.add(s);
			defaulters.add("cheaterslist");
			for(String s: cheats) defaulters.add(s);
		}
		con.close();
		return defaulters;
	}

	public void checkFare(String btaddr, int netBalance) throws Exception
	{
		checkSource.setString(1,btaddr);
		ResultSet rs = checkSource.executeQuery();
		while(rs.next())
		{
			String source_id = rs.getString("source_id");
			final String cell = rs.getString("cell_no");
			if(source_id.equalsIgnoreCase("false"))	// if d id hasnt been set
			{	
				setStation.setString(1,station);
				setStation.setString(2,btaddr);
				setStation.executeUpdate();
			}
			else
			{
				int fare=-1;
				int from = Integer.parseInt(scnfg.getInitParameter(source_id));
				int to = Integer.parseInt(scnfg.getInitParameter(station));
				if(to>=from) fare=to-from;
				else fare=from-to;
				int finalBalance = netBalance-fare;
				updatBal.setInt(1,finalBalance);
				updatBal.setString(2,"false");
				updatBal.setString(3,btaddr);
				updatBal.executeUpdate();			
				
				insertLog.setString(1,btaddr);
				insertLog.setString(2,source_id);
				insertLog.setString(3,station);
				java.sql.Date date= new java.sql.Date(new java.util.Date().getTime());
				insertLog.setDate(4,date);
				insertLog.setInt(5,fare);
				insertLog.executeUpdate();
							
				//--------------------------Send Message------------------------
				final String message = "::Journey_Details:: FROM: "+source_id+" TO: "+station+"  FARE: "+fare+" INR  BALANCE: "+finalBalance+" INR DATE: "+date+".  Good Day.";
				new Thread(new Runnable()
				{
					public void run()
					{
						try
						{
							SmsPost.sendTextMessage(message,cell);
						}catch(Exception e){System.out.println("Exception in sending the SMS "+e);}
					}
				}).start();
			}
		}
	}  
}