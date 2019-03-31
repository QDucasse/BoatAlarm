package users;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Quentin Ducasse
 *
 */

public class DummyAdminList {
	List<Administrator> administratorList = new ArrayList<Administrator>();

	public DummyAdminList() {
		Administrator admin1 = new Administrator("Admin1", "1234");
		Administrator admin2 = new Administrator("Admin2", "1234");
		Administrator admin3 = new Administrator("Admin3", "1234");
		Administrator admin4 = new Administrator("Admin4", "1234");

		administratorList.add(admin1);
		administratorList.add(admin2);
		administratorList.add(admin3);
		administratorList.add(admin4);
	}

	public List<Administrator> getAdministratorList() {
		return administratorList;
	}

}
