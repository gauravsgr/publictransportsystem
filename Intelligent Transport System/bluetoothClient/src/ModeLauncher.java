import javax.bluetooth.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class ModeLauncher implements Runnable
{
	String url=null;
	BufferedReader in;
	ArrayList<RemoteDevice> deviceList;
		
	public void sendRemoteDevices() throws Exception 
	{

		RemoteDeviceDiscovery rdd= new RemoteDeviceDiscovery();
		deviceList= rdd.searchDevices();
		if(deviceList.size()==0) 
		{
			synchronized(Login.lock)
			{
				Login.lock.notifyAll();
			}
			Login.previousDeviceList=deviceList;//Stores devicelist for removing redundancy in the next iteration
			return;
		}
		
		URL url = new URL(Login.urladd+"/verify.do");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		int i=0;
		for(RemoteDevice rd: deviceList)
			if(!Login.previousDeviceList.contains(rd))
			{
				out.write("param"+(i++)+"="+URLEncoder.encode(rd.getBluetoothAddress(),"UTF-8")+"&");
				System.out.println(rd.getBluetoothAddress());
			}
		out.write("station="+URLEncoder.encode(Login.station));
		Login.previousDeviceList=deviceList;//Stores devicelist for removing redundancy in the next iteration
		out.close();
		
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine=in.readLine();
		if(!inputLine.equalsIgnoreCase("passed")) 
		{
			new Thread(this).start();
		}
		else 
		{
			in.close();
			synchronized(Login.lock)
			{
				Login.lock.notifyAll();
			}
		}
		System.out.println("This ended now...");
    }
	
	public void run()
	{
		ArrayList<String> bllist=new ArrayList<String>();
		ArrayList<String> chlist=new ArrayList<String>();
		String inputline="";
		try
		{
			boolean lowbal=true;
			while ((inputline = in.readLine()) != null) 
			{
				if(inputline.equalsIgnoreCase("cheaterslist"))
				{
					lowbal=false;
					continue;
				}
				if(lowbal) bllist.add(inputline);
				else chlist.add(inputline);
			}
			in.close();
			File f=new File("Tracker.log");
			f.createNewFile();
			PrintWriter out = new PrintWriter(new FileWriter(f,true)) ;
			out.println(new Date());
			out.println("C H E A T E R S  L I S T");
			for(String s: chlist) 
				for(RemoteDevice rd: deviceList)
					if(rd.getBluetoothAddress().equals(s)) out.println("  "+s+" Friendly Name: "+rd.getFriendlyName(false));
			//for(String s: chlist) out.println("\t"+s);
			out.println("L O W  B A L A N C E  L I S T");
			for (String s: bllist) out.println("  "+s);
			out.println();
			out.println();
			out.println();
			out.close() ;
		}catch(Exception e){System.out.println("Caught Exception now "+e);}
		synchronized(Login.lock)
		{
			Login.mlPlay=true;
			Login.lock.notifyAll();
			new Thread(new Runnable()
			{
				public void run(){
					new PlayWav().play();	//executing sound in a separate thread in order to complete d block by synchronous start n notify
				}
			}).start();
		}
	}
}