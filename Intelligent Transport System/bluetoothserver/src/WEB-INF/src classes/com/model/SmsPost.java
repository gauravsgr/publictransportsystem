package com.model;
import java.net.*;
import java.io.*;

public class SmsPost 
{
	public static void main(String[]args)
	{	
		String message="";
		for(int i=0;i<args.length-1;i++) message+=args[i]+" ";
		String recpt=args[args.length-1];
		message+="Have a Good Day";
		System.out.println(message);
		try
		{
			sendTextMessage(message,recpt);
		}catch(Exception e){System.out.println("Exception caught: "+e);}
	}
	
    public static void sendTextMessage(String msg,String rcpt) throws Exception 
	{

		URL url = new URL("http://s2.freesmsapi.com/messages/send");
	
		String skey = URLEncoder.encode("69671ae28e3daa4ac8a03c45057c9f8f", "UTF-8");
		String message = URLEncoder.encode(msg, "UTF-8");
		String recipient=URLEncoder.encode(rcpt,"UTF-8");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write("skey="+ skey+"&");
		out.write("message="+ message+"&");
		out.write("recipient="+ recipient);
		out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
    }
}
/**
			N O T E : -
	1. You always have to set the responce code in your Servlet Code to read the content(look at d BeerSelect servlet code), else http 500 error WATCH IT!!!!
	
	2. This also handles the RequestDispatcher n makes u find its code as with the new SelectBeer.do forwards to a JSP and it works fine with that ;)...still has to check its validity for the sendRedirect method.
	
*/