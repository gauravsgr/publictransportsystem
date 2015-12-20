package com.model;
import java.sql.*;

public class CreateDataStorage
{
	Connection con;
    String url;
    String dbName;
    String driverName;
    String userName;   
	String password;
	
	public CreateDataStorage(String urln, String dbNamen, String userNamen, String passwordn, String driverNamen)
	{
		url = urln; dbName = dbNamen; userName = userNamen; password = passwordn; driverName = driverNamen;	
	}
	
	public static void main(String[] args) 
	{
		System.out.println("creating all the stuff ");
		com.model.CreateDataStorage cds = new com.model.CreateDataStorage("jdbc:mysql://localhost:3306/","bluetransport","root","root","com.mysql.jdbc.Driver");
		cds.initialize();
    }	
  
	public void initialize()
	{
		try
		{
			Class.forName(driverName);
			con = DriverManager.getConnection(url, userName, password);
			createDataBase();
			con.close();
			
			con = DriverManager.getConnection(url+dbName, userName, password);
			createTables();
			con.close();
		}
		catch (Exception e){  e.printStackTrace();  }
	}
  
	void createDataBase()
	{
		try
		{
			Statement st = con.createStatement();
			st.executeUpdate("CREATE DATABASE BLUETRANSPORT");
			System.out.println("Database BLUETRANSPORT created successfully!!!");
		}
		catch (SQLException s)
		{
			System.out.println("\n "  +s);
		}	
	}
	
	void createTables()
	{
		//----------------CREATING REGISTERED_USERS DATATABLE----------------
		try
		{
			Statement st = con.createStatement();
			String table = "CREATE TABLE REGISTERED_USERS "+"(BLUETOOTH_ADR VARCHAR(13) NOT NULL, "+"NAME VARCHAR(40), " +
					"EMEI VARCHAR(15), "+"DOB VARCHAR(20), " +"CELL_NO VARCHAR(20), " +"SOURCE_ID VARCHAR(20), " +
					"BALANCE INTEGER (10), " +"primary key(BLUETOOTH_ADR))";
			st.executeUpdate(table);
			System.out.println("Table REGISTERED_USERS created");
		}
		catch(SQLException s)
		{
			System.out.println("\n "+s);
		}
		//----------------CREATING TRAVELERS_LOG DATATABLE----------------
		try
		{
			Statement st = con.createStatement();
			String table = "CREATE TABLE TRAVELERS_LOG "+"(BLUETOOTH_ADR VARCHAR(15) NOT NULL, "+"SOURCE VARCHAR(15), " +
					"DESTINATION VARCHAR(15), "+"DATE_VALUE DATE, " +"FARE INTEGER(20))";
			st.executeUpdate(table);
			System.out.println("Table TRAVELERS_LOG created");
		}
		catch(SQLException s)
		{
			System.out.println("\n "+s);
		}
		//----------------CREATING USERS_INFO DATATABLE----------------
		try
		{
			Statement st = con.createStatement();
			String table = "CREATE TABLE USERS_INFO "+"(BLUETOOTH_ADR VARCHAR(13) NOT NULL, "+"USERNAME VARCHAR(40), " +"PASSWORD VARCHAR(30), "
			+"NAME VARCHAR (30), "+"EMAIL_ID VARCHAR (40), "+"GENDER VARCHAR (6), "+"STREET VARCHAR (30), "+"CITY VARCHAR (20), "
			+"STATE VARCHAR (20), "+"COUNTRY VARCHAR (15), "+"ZIP VARCHAR (10), "+"primary key(BLUETOOTH_ADR))";
			st.executeUpdate(table);
			System.out.println("Table USERS_INFO created");
		}
		catch(SQLException s)
		{
			System.out.println("\n "+s);
		}
	}
}