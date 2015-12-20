import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.bluetooth.*;
public class Login extends JFrame implements ActionListener
{
	static String urladd, station;
	static final String lock="";
	static boolean mlPlay=false;
	static ArrayList<RemoteDevice> previousDeviceList=new ArrayList<RemoteDevice>();
	JLabel url,passkey;
	JTextField urlfld;
	JPasswordField passfld;
	JButton enter;
	JPanel one,two,three;
	int modeSelected, time;
	
	public Login()
	{
		super("  Authorization Panel");
	}
	
	public static void main(String...arg) throws Exception
	{
		Thread.currentThread().sleep(3000);
		Login l=new Login();
		l.createFrame();
	}
	
	public void createFrame()
	{
		url=new JLabel("     URL      ");
		passkey=new JLabel("PASSKEY");
		urlfld=new JTextField(15);
		passfld=new JPasswordField(15);
		enter=new JButton(" Log In ");

		one=new JPanel();
		one.add(url); one.add(urlfld);
		two=new JPanel();
		two.add(passkey); two.add(passfld);
		three=new JPanel();
		three.add(enter); 
		add(one,BorderLayout.NORTH);
		add(two);
		add(three,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(450,300,300,130);
		setVisible(true);
		urlfld.addActionListener(this);
		passfld.addActionListener(this);
		enter.addActionListener(this);
	}
	
	//---------------Doing up Event Handling---------------------
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==enter)
		{
			setVisible(false);

			try{
			//------Connecting to the URL specified----------------
				String passkey=URLEncoder.encode(passfld.getText(),"UTF-8");
				urladd=urlfld.getText();
				URL url = new URL(urladd+"/authorization.do");
	
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
		
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write("passkey="+ passkey);
				out.close();
				station = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();		
				if(station.equalsIgnoreCase("null")) //returns null if passkey is invalid thats it!!!
				{	
					JOptionPane.showMessageDialog(this,"Invalid Details Furnished ");
					System.exit(0);
				}
				JOptionPane.showMessageDialog(this,"Logged in Successfully.");
				selectMode();
				while(true)//fully automatic now with this loop!!!
				{
					synchronized(lock)
					{
						new Thread(new Runnable()
						{
							public void run()
							{
								try
								{
									new ModeLauncher().sendRemoteDevices();					
								}catch(Exception e)
								{
									System.out.println("Exception in Login's CheckFrame "+e);
								}
								
							}
						}).start();
						lock.wait();
					}
					if(Login.mlPlay)// Playing sound and all
					{
						JOptionPane.showMessageDialog(this,"High Alert: INTRUDER DETECTED!!!");
						Login.mlPlay=PlayWav.loop=false;
					}
					if(modeSelected==0) JOptionPane.showMessageDialog(this,"Press OK to release the Search Pulse");
					else Thread.currentThread().sleep(time);
				}
			
			}catch(Exception e)
			{
				System.out.println("Exception found "+e);
				JOptionPane.showMessageDialog(this,"Please Enter data in all the Fields Correctly");
				setVisible(true);
			}
		}
	}
	
	public void selectMode()
	{
			
		String[] mode = { "Manual", "Automatic" };
		modeSelected = JOptionPane.showOptionDialog(this,"Please select the Mode of Operaion","Operation Mode",0,JOptionPane.INFORMATION_MESSAGE,null,mode,mode[0]);
		if(modeSelected==1)//Automatic
		{
			String interval = JOptionPane.showInputDialog(this,"Enter the Interval for firing pulse(in seconds).");				
			time=Integer.parseInt(interval)*1000;
			System.out.println(time);
		}
	}
}