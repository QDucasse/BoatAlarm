package users;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class DummySubscriberList {
	List<Subscriber> subscriberList = new ArrayList<Subscriber>();
	Confidence confPerson = new Confidence("mail.confidence@test.com", "000000000");

	public DummySubscriberList() {
		Subscriber sub1 = new Subscriber("QDucasse", "1234", "Quentin Ducasse", "2 Rue François Verny",
				"quentin.ducasse@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 12),
				"mail.confidence@test.com", "000000000", "48500", "LeGoëland", "Voilier", "Catamaran");

		Subscriber sub2 = new Subscriber("GLeBoucher", "1234", "Guillaume Le Boucher", "2 Rue François Verny",
				"guillaume.le_boucher@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 13),
				"mail.confidence@test.com", "000000000", "48501", "L'Agile", "Voilier", "Dériveur");

		Subscriber sub3 = new Subscriber("GTanios", "1234", "Georges Tanios", "2 Rue François Verny",
				"georges.tanios@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 14), "mail.confidence@test.com",
				"000000000", "48502", "LeSolid", "Moteur", "Yacht");

		Subscriber sub4 = new Subscriber("MSouafyan", "1234", "Mahmoud Souafyan", "2 Rue François Verny",
				"mahmoud.souafyan@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 15),
				"mail.confidence@test.com", "000000000", "48503", "L'ingénieurSecurité", "Moteur", "Yacht");

		Subscriber sub5 = new Subscriber("JChampeau", "1234", "Joël Champeau", "2 Rue François Verny",
				"joel.champeau@ensta-bretagne.fr", LocalDate.of(2014, Month.DECEMBER, 15),
				"mail.confidence@test.com", "000000000", "48504", "Brest", "Moteur", "Yacht");

		subscriberList.add(sub1);
		subscriberList.add(sub2);
		subscriberList.add(sub3);
		subscriberList.add(sub4);
		subscriberList.add(sub5);
	}

	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}

}
