package users;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SubscriberTest {

	Subscriber sub = new Subscriber("QDucasse", "1234", "Quentin Ducasse", "2 Rue François Verny",
			"quentin.ducasse@ensta-bretagne.org", LocalDate.of(2014, Month.DECEMBER, 12),
			"mail.confidence@test.com", "000000000",
			"48500","LeGoëland", "Voilier", "Catamaran");
	
	@Test
	void testCreateEmailList() {
		List<String> emailList = new ArrayList<String>();
		emailList.add("quentin.ducasse@ensta-bretagne.org");
		emailList.add("mail.confidence@test.com");
		List<String> emailListFromFunction = new ArrayList<String>();
		emailListFromFunction = sub.createEmailList();
		assert(emailList.equals(emailListFromFunction));
	}

}
