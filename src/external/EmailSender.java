package external;

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

/**
 * <b>EmailSender allows the system to send emails through a gmail account</b>
 * <p>
 * An EmailSender instance is characterized by the following :
 * </p>
 * <ul>
 * <li>Properties that characterize the port,authentification,ttls of the
 * mail</li>
 * <li>A session to allow the shipment of the email</li>
 * <li>A message to transfer</li>
 * </ul>
 * 
 * @author Quentin Ducasse
 */

public class EmailSender {
	/**
	 * The properties of the email
	 */
	Properties emailProperties;
	/**
	 * The session to send the email
	 */
	Session mailSession;
	/**
	 * The message itself
	 */
	MimeMessage emailMessage;

	/**
	 * Sets the different properties of the shipment of the email: port,auth,ttls
	 */

	public void setMailServerProperties() {

		String emailPort = "587"; // gmail's smtp port
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	/**
	 * Creates the body of the email, the subject and sets the session
	 * 
	 * @param sub The receiver of the email (him and the emails within his
	 *            confidences)
	 * @throws AddressException   Wrong address
	 * @throws MessagingException Connection issue
	 */

	public void createEmailMessage(Subscriber sub) throws AddressException, MessagingException {
		List<String> toEmails = sub.createEmailList();
		String emailSubject = "URGENT! Your boat alarm has been triggered!";
		String emailBody = "WARNING" + '\n' + "Your boat alarm has been triggered on " + sub.getBoat().getName()
				+ ", please contact us asap" + '\n' + "BoatAlarm Corp." + '\n';

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (String s : toEmails) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
		}

		emailMessage.setSubject(emailSubject);
		// emailMessage.setContent(emailBody, "text/html");//for a html email
		emailMessage.setText(emailBody);// for a text email
	}

	/**
	 * The actual shipment of the email: Connection to the host, shipment of the
	 * message then closing the connection
	 * 
	 * @throws AddressException   Wrong address
	 * @throws MessagingException Connection issue
	 */

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

	/**
	 * Gathering of the previous functions in one with a simple signature
	 * 
	 * @param sub The receiver of the email (him and the emails within his
	 *            confidences)
	 * @throws AddressException   Wrong address
	 * @throws MessagingException Connection issue
	 */

	public void sendEmailToSubscriber(Subscriber sub) throws AddressException, MessagingException {
		this.setMailServerProperties();
		this.createEmailMessage(sub);
		this.sendEmail();
	}

}