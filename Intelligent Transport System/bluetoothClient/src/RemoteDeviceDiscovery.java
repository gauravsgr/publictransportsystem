import javax.bluetooth.*;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Minimal Device Discovery example. The startInquiry() method is non-blocking as it creates one seperate thread for searching all remote devices 
 */
public class RemoteDeviceDiscovery implements DiscoveryListener
{

	static Object inquiryCompletedEvent = new Object();// forcefully had 2 keep it static, couldn't figure out d problem
	//String inquiryCompletedEvent=new String("puzzled");// works fine with both d lower options
	//Object inquiryCompletedEvent = "hi";
	
	static ArrayList<RemoteDevice> devicesDiscovered = new ArrayList<RemoteDevice>();// RemoteDevices
	ArrayList<RemoteDevice> devices=new ArrayList<RemoteDevice>();// final devices retained
		
	
    public ArrayList<RemoteDevice> searchDevices() throws IOException, InterruptedException 
	{
		
        DiscoveryListener listener = new RemoteDeviceDiscovery();
		devicesDiscovered.clear();
        boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
		System.out.println("Inquiry Started "+started+" in Main Thread "+Thread.currentThread());
		synchronized(inquiryCompletedEvent)
		{ 
			inquiryCompletedEvent.wait(); 
		}
		System.out.println("Size of Arraylist is "+devicesDiscovered.size());
		for(RemoteDevice r: devicesDiscovered) devices.add(r);
		return devices;
    }
	
	//------------------Implemented Methods of DiscoveryListener--------------------
	
	public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) 
	{
		if(cod.getMajorDeviceClass()==512) devicesDiscovered.add(btDevice);
    }

    public void inquiryCompleted(int discType) 
	{
		synchronized(inquiryCompletedEvent)
		{
			inquiryCompletedEvent.notifyAll();
		}
    }

    public void serviceSearchCompleted(int transID, int respCode) { }
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) { }
}