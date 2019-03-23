package external;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import users.Subscriber;

public class EmailSender {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void main(String args[]) throws AddressException, MessagingException {

		EmailSender emailSender = new EmailSender();
		Subscriber sub = new Subscriber("QDucasse", "1234", "Quentin Ducasse", "2 Rue François Verny",
				"quentin.ducasse@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 12), 
				"guillaume.le_boucher@ensta-bretagne.org","0000"
				, "48500","Le Goëland", "Voilier", "Catamaran");
		emailSender.setMailServerProperties();
		emailSender.createEmailMessage(sub);
		emailSender.sendEmail();
	}

	public void setMailServerProperties() {

		String emailPort = "587"; // gmail's smtp port
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(Subscriber sub) throws AddressException, MessagingException {
		List<String> toEmails = sub.createEmailList();
		String emailSubject = "URGENT! Your boat alarm has been triggered!";
		String emailBody = "WARNING" + '\n' + "Your boat alarm has been triggered, please contact us asap" + '\n'
				+ "BoatAlarm Corp." + '\n' + '\n' + '\n' + "Testing the email sending!!";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (String s : toEmails) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
		}

		emailMessage.setSubject(emailSubject);
		// emailMessage.setContent(emailBody, "text/html");//for a html email
		emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "boatalarmco";// just the id alone without @gmail.com
		String fromUserEmailPassword = "B04t4l4rm!";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

}