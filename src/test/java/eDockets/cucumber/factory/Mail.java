package eDockets.cucumber.factory;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class Mail {
	public static void main(String[] args)
	//public static void mail(String file)
	//public static void mail(String ToAddress1,String CCAddress1,String BCCAddress1, String FromAddress1, String SubjectLine1, String BodyMessage1, String AttachedFileLocation1)
	{
		 try{
			  String host = "smtp.gmail.com";
			  
		      // Get system properties
		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);
		      properties.put("mail.stmp.user" , "chaitanyabuddi123@gmail.com");
		      properties.put("mail.smtp.auth", "true");
	            properties.put("mail.smtp.starttls.enable", "true");
	            
		      properties.setProperty("mail.smtp.port","465");
		      
		      properties.put("mail.smtp.password", "bvntopzvtusmsmln");
		      properties.put("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class", 
		            "javax.net.ssl.SSLSocketFactory");
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
	    	  
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress("QATeam@gmail.com"));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress("chaitanyabuddi123@gmail.com"));
	         message.addRecipient(Message.RecipientType.CC, new InternetAddress("chaitu.090418@gmail.com"));
	      //   message.addRecipient(Message.RecipientType.BCC, new InternetAddress(BCCAddress1));

	         // Set Subject: header field
	         message.setSubject("Automation Status");
	         
	         
	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message

	         messageBodyPart.setContent("<h2>Please find the attached eDockets Cloud Application Automation Report. Download and view the Report Status</h2>", "text/html");
	         
	         
	         
	         // Create a multipart message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	         // Part two is attachment

	        // String filename = System.getProperty("user.dir") + "/target/cucumber.html";
	         String filename = System.getProperty("user.dir") + "/SparkReport/MySparkReport.html";
	     //    String filename = System.getProperty("user.dir") + "/PDFReport/ExtentReport.pdf";
	         System.out.println(filename);
	        // System.out.println(filename.length());
	       //Create data source to attach the file in mail

	         DataSource source = new FileDataSource(filename);

	         messageBodyPart.setDataHandler(new DataHandler(source));

	         messageBodyPart.setFileName(filename);

	         multipart.addBodyPart(messageBodyPart);

	         message.setContent(multipart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport transport = session.getTransport("smtp");
	         transport.connect(host, "chaitanyabuddi123@gmail.com","bvntopzvtusmsmln");

	         transport.sendMessage(message, message.getAllRecipients());

	         transport.close();
	        // Transport.send(message,"chaitanyabuddi123@gmail.com","bvntopzvtusmsmln");
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		 
	}
}
