package com.model;
import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccountEmail
{
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String emailSubjectTxt = "Intelligent Transport System Account Details";
	private static final String emailFromAddress = "admin@intelligent-transport.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	public static void sendEmail(String email, String name, String username, String password)
	{
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		AccountEmail ae = new AccountEmail();
		String[] sendTo=new String[1];
		sendTo[0]=email;
		
		
		String emailMsgTxt= "<img src=\"http://www.lightrail.com/photos/stlouis/stlouis_logo.GIF\" "+"align=\"middle\"></BR>"+
		"<H2>Dear "+name+",</H2>"+
		" Thankyou for becoming a memeber of our Intelligent Transport System. Kindly note down your account details:-<BR><BR>"+
		"USERNAME = "+username+"<BR><BR>"+
		"PASSWORD = "+password+"<BR><BR><BR>"+
		"You are now entitled to a host of services and comforts.<BR><BR>"+
		
		"<H2>Added Value to your Bucks</H2><P> At 120 km/hr in the AC coaches the joy of travelling gets doubled when you as the subscriber of the \"Bluetooth Service\" gets special points for each trip you make. These points can be used to buy stuff from the shops at the station campus be it a pizza from the Pizza Hut, a pint of Corona or just a froaty cappichino from our refreshment center. Further, you also get entitled to special lucky draws held quaterly. Besides this, every month first 100 users get special cash back offers and 20% discount on their first 3 trips. Cheers!</P><BR>"+
		
		"<H2>Remain Updated</H2><P> Now you just dont need to listen to your radio stations and social networking accounts to stay updated of the busy routes to avoid, and smooth routes to switch to. With our artificialy inteligent system you get updates right on your cell for the routes to avoid, which are stuck hard and also suggest alternate routes to take and yeah we suggest routes even other than present in our system to save you from inconvenience. Thereby, at your service where ever you go.	</p><BR>"+

		"<h2>Widest Network</H2><p> The bluetooth service is lauched every where on the official routes of Rapid transport System, which means you could just never get out of route to your destination. Talks are on with other popular modes of Transportation for the integration of the Bluetooth service with them, so that you have to flexiblity to switch modes as per requirements.</p><BR>"+
		"<h3>Yours Truly,</H3> <h3>Intelligent Transport System Team</H3>"+
		
		"<IMG SRC=\"http://www.thehomeshow.com/images/metro_logo.jpg\" "+"</A>"+
		"</BR>";
		try{
			ae.sendSSLMessage(sendTo, ae.emailSubjectTxt, emailMsgTxt, ae.emailFromAddress);
		}catch(Exception e){System.out.println("Exception in AccountEmail "+e);}
	}
	
	public void sendSSLMessage(String recipients[], String subject, String message, String from) throws MessagingException 
	{
		boolean debug = true;
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication("gaurav.sgr", "gau2sag1");
			}
		});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) addressTo[i] = new InternetAddress(recipients[i]);
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		
		msg.setSubject(subject); // Setting the Subject and Content Typec
		msg.setContent(message, "text/html"); //msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}