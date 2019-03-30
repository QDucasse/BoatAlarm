package external;

import java.time.LocalDate;
import java.time.Month;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import users.Subscriber;

public class EmailSenderDemo {
	
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
}
