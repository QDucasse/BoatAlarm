package external;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class EmailSender {  
	
 public static void main(String[] args) {  
  
  String host="smtp.gmail.com";  
  final String user="boatalarmco@gmail.com"; 
  final String password="B04t4l4rm!";
    
  String to="quentin.ducasse@ensta-bretagne.org";  
  
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.com" ,host);  
   props.put("mail.smtp.auth", "true"); 
   props.put("mail.smtp.port", 465);
   
   Session session = Session.getInstance(props,  
		   new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("ALERT ! Your boat alarm has been triggered!");  
     message.setText("Your boat alarm has been triggered! Please contact us and follow your boat on our application.\n"
     		+ "\nBoat Alarm Corp.");  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("Email sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
 }  
}  
